package Tablas;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class TablaAfterEffects extends JTable{
    public TablaAfterEffects(DefaultTableModel dtm){
        getTableHeader().setBackground(Color.ORANGE);
        setModel(dtm);

        for (int i = 0; i<getColumnCount(); i++){

            Object valorCelda = getValueAt(0, i).toString();
            Font fuenteCelda = getFont();
            FontMetrics fontMetrics = getFontMetrics(fuenteCelda);
            int anchoTexto = fontMetrics.stringWidth(valorCelda.toString());
            System.out.println(getColumnName(i));
            getColumnModel().getColumn(i).setPreferredWidth(anchoTexto*3+10);
        }

        JTableHeader header = getTableHeader();
        header.setBackground(Color.decode("#38B6FF"));
        header.setPreferredSize(new Dimension(700,40));
        header.setFont(new Font("Arial", Font.PLAIN, 15));

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        headerRenderer.setOpaque(false);

        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTableHeader header = (JTableHeader) e.getSource();
                    JTable target = header.getTable();
                    int columnIndex = target.getColumnModel().getColumnIndexAtX(e.getX());
                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) target.getModel());

                    // Si la columna es la de Fecha de Nacimiento, utilizar el Comparator para fechas
                    if (columnIndex == 2) {
                        sorter.setComparator(columnIndex, (obj1, obj2) -> {
                            String dateString1 = obj1.toString();
                            String dateString2 = obj2.toString();

                            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            try {
                                Date date1 = format.parse(dateString1);
                                Date date2 = format.parse(dateString2);
                                return date1.compareTo(date2);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }

                            return 0;
                        });
                    }

                    target.setRowSorter(sorter);
                    sorter.toggleSortOrder(columnIndex);
                }
            }
        });

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                // Obtener el componente de la celda
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Cambiar el color de fondo de la fila seleccionada
                Font font = c.getFont();
                c.setFont(font.deriveFont(Font.BOLD, 14));
                if (isSelected) {
                    setBorder(noFocusBorder);
                    c.setBackground(Color.RED); // Cambia el color a azul, puedes elegir el color que desees
                    c.setForeground(Color.WHITE); // Cambia el color del texto a blanco
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }

                return c;
            }
        };

        // Asignar el renderer personalizado a todas las columnas de la tabla
        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        setSelectionBackground(getBackground());
        setSelectionForeground(getForeground());
        Random rnd = new Random();
        int asd = rnd.nextInt(100);
        exportTable(this,"src/"+asd+".xlsx");

        JScrollPane sp = new JScrollPane(this);

        this.repaint();
        this.revalidate();
    }

    public static void exportTable(JTable table, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Datos");

            // Obtener los datos de la JTable
            TableModel model = table.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            // Crear la fila para los nombres de las columnas
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < colCount; col++) {
                String columnName = model.getColumnName(col);
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columnName);
            }

            // Crear las filas y celdas en Excel para los datos
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < colCount; col++) {
                    Object value = model.getValueAt(row, col);
                    Cell cell = excelRow.createCell(col);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            // Ajustar el ancho de las columnas automáticamente
            for (int col = 0; col < colCount; col++) {
                sheet.autoSizeColumn(col);
            }

            // Agregar una tabla a la hoja de cálculo
            XSSFSheet xssfSheet = (XSSFSheet) sheet;
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();
            int firstCol = headerRow.getFirstCellNum();
            int lastCol = headerRow.getLastCellNum() - 1;
            // Obtener las referencias de las celdas para el rango de la tabla
            CellReference topLeft = new CellReference(firstRow, firstCol);
            CellReference bottomRight = new CellReference(lastRow, lastCol);

            // Crear la referencia del rango de celdas
            String tableRange = topLeft.formatAsString() + ":" + bottomRight.formatAsString();
            AreaReference areaReference = new AreaReference(tableRange, SpreadsheetVersion.EXCEL2007);

            XSSFTable tableFormat = xssfSheet.createTable(areaReference);
            CTTable ctTable = tableFormat.getCTTable();
            ctTable.addNewAutoFilter().setRef(tableRange);

            // Aplicar estilos de tabla
            CTTableStyleInfo styleInfo = ctTable.addNewTableStyleInfo();
            styleInfo.setName("TableStyleMedium2");

            // Escribir los datos en un archivo Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Exportación exitosa a Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
