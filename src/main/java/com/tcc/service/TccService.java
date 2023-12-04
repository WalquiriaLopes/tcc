package com.tcc.service;

import com.tcc.entities.PlanilhaEntity;
import com.tcc.repository.PlanilhaRepository;
import com.tcc.selenium.SeleniumOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TccService {

    @Autowired
    private PlanilhaRepository planilhaRepository;

    public void execute(){

        this.execute(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }

    public void execute(String data){
        SeleniumOptions seleniumOptions = new SeleniumOptions();
        seleniumOptions.defineBrowserDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "arquivos" + File.separator);
//        options.addArguments("--headless");
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);

        try {

            executaDemanda(data, driver);

        } catch (Exception e) {
            System.out.println("ERRO AO BAIXAR PLANILHA NA DATA " + data);
            driver.quit();
        }
    }

    private void executaDemanda(String data, WebDriver driver) throws Exception {
        Thread.sleep(7000);
        driver.get("https://consultafns.saude.gov.br/#/repasse-dia");
        Thread.sleep(7000);
        driver.findElement(By.id("repasseDiaCtrl_pesquisa_data")).sendKeys(data);
        Thread.sleep(7000);
        driver.findElement(By.xpath("//button[contains(@ng-click, 'repasseDiaCtrl.pesquisar()')]")).click();
        Thread.sleep(7000);

        for(int i = 1; i < 11; i++){
            if(!driver.findElements(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")).isEmpty()
                    && !driver.findElements(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")).get(0).getText().toLowerCase().contains("nenhum registro encontrado")){

                scrollElement(driver, driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")));
                String nomeBloco = driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div["+ i +"]/div[1]/h4/a/div[1]")).getText();
                driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")).click();
                Thread.sleep(2000);

                for(int j = 1; j< 11; j++){
                    if(!driver.findElements(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]/div[2]/div/accordion/div/div["+j+"]")).isEmpty()){
                        scrollElement(driver, driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")));
                        driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")).click();
                        Thread.sleep(2000);
                        scrollElement(driver, driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]/div[2]/div/accordion/div/div["+j+"]")));
                        driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]/div[2]/div/accordion/div/div["+j+"]")).click();
                        String nomeGrupo = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div["+i+"]/div[2]/div/accordion/div/div["+j+"]/div[1]/h4/a/div[1]")).getText();

                        for(int k = 1; k<11; k++) {
                            if (!driver.findElements(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]")).isEmpty()) {
                                Thread.sleep(2000);
                                scrollElement(driver, driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")));
                                driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]")).click();
                                Thread.sleep(2000);
                                scrollElement(driver, driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]/div[2]/div/accordion/div/div["+j+"]")));
                                driver.findElement(By.xpath("//body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div/div/div/div[2]/accordion/div/div["+ i +"]/div[2]/div/accordion/div/div["+j+"]")).click();
                                Thread.sleep(2000);

                                if(driver.findElements(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]/td[5]/button")).isEmpty()){
                                    scrollElement(driver, driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]/td[6]/button")));
                                    driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]/td[6]/button")).click();
                                    Thread.sleep(2000);
                                }
                                else{
                                    scrollElement(driver, driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]/td[5]/button")));
                                    driver.findElement(By.xpath("/html/body/div[2]/section/div/div[3]/div/div[3]/div[2]/div[3]/div[1]/div/div/div[2]/accordion/div/div[" + i + "]/div[2]/div/accordion/div/div[" + j + "]/div[2]/div/div/table/tbody/tr[" + k + "]/td[5]/button")).click();
                                    Thread.sleep(2000);
                                }

                                scrollElement(driver, driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section/div/div[3]/div/div/div[2]/div[2]/div[5]/div/div/button[2]")));
                                driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section/div/div[3]/div/div/div[2]/div[2]/div[5]/div/div/button[2]")).click();
                                Thread.sleep(7000);

                                File file = new File(System.getProperty("user.dir") + File.separator + "arquivos" + File.separator + "PlanilhaDetalhadaRepasseDia.xlsx");
                                List<PlanilhaEntity> casos = this.lerPlanilha(file, nomeBloco, nomeGrupo, data);
                                planilhaRepository.saveAll(casos);
                                file.delete();

                                scrollElement(driver, driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section/div/div[3]/div/div/div[2]/div[2]/div[5]/div/div/button[1]")));
                                driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section/div/div[3]/div/div/div[2]/div[2]/div[5]/div/div/button[1]")).click();
                                Thread.sleep(7000);
                            }
                        }
                    }
                }
            }
        }

        driver.quit();
    }

    public static void scrollElement(WebDriver driver, WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(5000);
    }
    public List<PlanilhaEntity> lerPlanilha(File file, String nomeBloco, String nomeGrupo, String data) throws Exception {

        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet planilha = workbook.getSheetAt(0);

            Iterator<Row> linhas = planilha.iterator();

            for (int i = 0; i < 1; i++) {
                linhas.next();
            }

            List<PlanilhaEntity> casosPlanilha = new ArrayList<>();

            while (linhas.hasNext()) {

                Row linha = linhas.next();

                PlanilhaEntity caso = new PlanilhaEntity();

                int celNumber = 0;

                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setUf(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setMunicipio(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setFavorecido(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setCpf_cnpj(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setNum_processo(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setNum_port(linha.getCell(celNumber).getStringCellValue());
                }
                celNumber++;
                if(Objects.nonNull(linha.getCell(celNumber))){
                    caso.setValor(linha.getCell(celNumber).getStringCellValue());
                }

                caso.setNomeBloco(nomeBloco);
                caso.setNomeGrupo(nomeGrupo);
                caso.setDataBuscada(data);

                casosPlanilha.add(caso);
            }

            return casosPlanilha;

        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao ler e gravar a planilha de casos: " + e.getMessage(), e);
        }
    }
}
