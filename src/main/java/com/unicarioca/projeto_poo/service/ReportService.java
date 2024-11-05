package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.reports.*;
import com.unicarioca.projeto_poo.repository.ReportRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReportRepository reportRepository;

    public MostSoldProductResponseDTO getMostSoldProductOfMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);
//        int qntMajor = 0;
//        int qntAux = 0;
//        Product major = new Product();
//        Product productAux;
//        for(int i = 0; i < products.size(); i++){
//            productAux = products.get(i);
//            qntAux++;
//            if(major == null){
//                major = productAux;
//                qntMajor++;
//            } else{
//                if(productAux == major){
//                    qntMajor++;
//                } else{
//                    if(productAux == products.get(i-1)){
//                        if(qntAux > qntMajor){
//                            major = productAux;
//                            qntMajor = qntAux;
//                        }
//                    } else{
//                        qntAux = 1;
//                    }
//                }
//            }
//        }
//
//        MostSaledProductResponseDTO productResponseDTO = new MostSaledProductResponseDTO(major, qntMajor);
//
//        return productResponseDTO;

        return (MostSoldProductResponseDTO) getMostElementInList(products);
    }

    public List<Product> getAllSoldProductsPerMonth(int month, int year){
        List<Order> orders = orderService.getOrdersByMonth(month, year);
        List<Product> productsOfMonth = new ArrayList<Product>();
        for(Order order : orders){
            for(Item item : order.getItems()){
                for(int i = 0; i < item.getQuantity(); i++){
                    productsOfMonth.add(item.getProduct());
                }
            }
        }

        Collections.sort(productsOfMonth, Comparator.comparing(p -> p.getName()));

        return productsOfMonth;
    }

    public List<ProductCategory> getAllCategoriesSoldPerMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            if(categories.isEmpty()){
                categories.add(product.getCategory());
            } else{
                if(!categories.contains(product.getCategory())){
                    categories.add(product.getCategory());
                }
            }
        }

        return categories;
    }

    public List<ProductCategory> getAllCategoriesSoldPerMonthWithoutFilter(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            categories.add(product.getCategory());
        }

        return categories;
    }

    public MostSoldCategoryResponseDTO getMostSoldCategoryOfMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            categories.add(product.getCategory());
        }

        Collections.sort(categories, Comparator.comparing(c -> c.getName()));

//        int qntMajor = 0;
//        int qntAux = 0;
//        ProductCategory major = new ProductCategory();
//        ProductCategory categoryAux;
//        for(int i = 0; i < categories.size(); i++){
//            categoryAux = categories.get(i);
//            qntAux++;
//            if(major == null){
//                major = categoryAux;
//                qntMajor++;
//            } else{
//                if(categoryAux == major){
//                    qntMajor++;
//                } else{
//                    if(categoryAux == categories.get(i-1)){
//                        if(qntAux > qntMajor){
//                            major = categoryAux;
//                            qntMajor = qntAux;
//                        }
//                    } else{
//                        qntAux = 1;
//                    }
//                }
//            }
//        }

        return (MostSoldCategoryResponseDTO) getMostElementInList(categories);

    }

    public Integer getNumberOfSalesPerCategory(ProductCategory category, int month, int year){
        List<ProductCategory> categories = getAllCategoriesSoldPerMonthWithoutFilter(month, year);
        int numSales = 0;

        for (ProductCategory productCategory : categories){
            if(productCategory.equals(category)){
                numSales++;
            }
        }

        return numSales;
    }

    public Float getTotalAmountCollectedInMonth(int month, int year){
        List<Order> orders = orderService.getOrdersByMonth(month, year);

        Iterator<Order> ordersIterator = orders.listIterator();

        Float amount = 0.0f;

        while (ordersIterator.hasNext()){
            amount += ordersIterator.next().getFinal_price();
        }

        return amount;
    }

    private Object getMostElementInList(List list){
        int qntMajor = 0;
        int qntAux = 0;
        Object major = new Object();
        Object objAux;
        for(int i = 0; i < list.size(); i++){
            objAux = list.get(i);
            qntAux++;
            if(major == null){
                major = objAux;
                qntMajor++;
            } else{
                if(objAux == major){
                    qntMajor++;
                } else{
                    if(objAux == list.get(i-1)){
                        if(qntAux > qntMajor){
                            major = objAux;
                            qntMajor = qntAux;
                        }
                    } else{
                        qntAux = 1;
                    }
                }
            }
        }

        return new MostElementInListResponseDTO(major, qntMajor);
    }

//    public Report getReportById(UUID id){
//        return reportRepository.findById(id).get();
//    }
//
//    public Report createReport(ReportRequestDTO reportDTO){
//        Report report = new Report();
//
//        report.setMonth(reportDTO.month());
//        report.setYear(reportDTO.year());
//        report.setFile_url(reportDTO.file_url());
//
//        reportRepository.save(report);
//
//        return report;
//    }

    public void generateReport(final String fileName, Integer month, Integer year){
        try(var workbook = new XSSFWorkbook();
            var outputStream = new FileOutputStream(fileName)){

            var spreadSheet = workbook.createSheet("Reports");

            int numberRow = 0;

            addHeader(spreadSheet, numberRow++);

            var row = spreadSheet.createRow(numberRow++);
            addCell(row, 0, 1);
            addCell(row, 0, getTotalAmountCollectedInMonth(month, year));

            workbook.write(outputStream);
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo!");
        }
    }

    private void addHeader(XSSFSheet spreadSheet, int numRow){
        var row = spreadSheet.createRow(numRow);

        addCell(row, 0, "Id");
        addCell(row, 0, "Amount");
    }

    private void addCell(Row row, int column, int value){
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void addCell(Row row, int column, float value){
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void addCell(Row row, int column, String value){
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }
}
