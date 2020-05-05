package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.dao.WageMapper;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import com.newer.service.impl.WageServiceImpl;
import com.newer.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    public WageMapper wageMapper;

//    @GetMapping("export")
//    public static void export(List<Map<String,Object>> list, LinkedHashMap<String, String> fieldMap, String filName,
//                              HttpServletResponse response) throws IOException {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet(filName + "1");//新建sheet
//        HSSFCellStyle contextstyle = wb.createCellStyle();
//        HSSFRow row = null;
//        int rowIndex = 0;
//        row = sheet.createRow(0);
//        row.setHeight((short) (22.50 * 20));//设置行高
//        // 定义存放英文字段名和中文字段名的数组
//        String[] enFields = new String[fieldMap.size()];
//        String[] cnFields = new String[fieldMap.size()];
//        // 填充数组
//        int count = 0;
//        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
//            enFields[count] = entry.getKey();
//
//            cnFields[count] = entry.getValue();
//            count++;
//        }
//        //设置表头
//        for (int i = 0; i < cnFields.length; i++) {
//            row.createCell(i).setCellValue(cnFields[i]);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (rowIndex > 65535) {
//                sheet = wb.createSheet(filName + "2");
//                row = sheet.createRow(0);
//                row.setHeight((short) (22.50 * 20));//设置行高
//                //设置表头
//                for (int k = 0; k < cnFields.length; k++) {
//                    row.createCell(k).setCellValue(cnFields[k]);
//                }
//            }
//            row = sheet.createRow(i + 1);
//            for (int j = 0; j < enFields.length; j++) {
//                Object data = list.get(i).get(enFields[j]);//获取第i行第j列所放数据
//                HSSFCell contentCell = row.createCell(j);
//                boolean isNum = false;
//                boolean isInteger = false;
//                if (data != null || "".equals(data)) {
//                    //判断data是否为数值型
//                    isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
//                    //判断data是否为整数（小数部分是否为0）
//                    isInteger = data.toString().matches("^[-\\+]?[\\d]*$");
//                }
//                if (isNum) {
//                    HSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
//                    if (isInteger) {
//                        //数据格式只显示整数
//                        contextstyle.setDataFormat(df.getBuiltinFormat("0"));
//                        // 设置单元格内容为double类型
//                        contentCell.setCellValue(data.toString());
//                    } else {
//                        //保留两位小数点
//                        contextstyle.setDataFormat(df.getBuiltinFormat("0.00"));
//                        // 设置单元格内容为double类型
//                        contentCell.setCellValue(Double.parseDouble(data.toString()));
//                        // 设置单元格格式
//                        contentCell.setCellStyle(contextstyle);
//                    }
//                } else {
//                    contentCell.setCellValue(list.get(i).get(enFields[j]) == null ? "" : list.get(i).get(enFields[j]).toString());
//                }
//            }
//            rowIndex++;
//        }
//        // 遍历集合数据，产生数据行
//        sheet.setDefaultRowHeight((short) (16.5 * 20));
//        //列宽自适应
//        for (int i = 0; i <= 13; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        OutputStream os = response.getOutputStream();
//        response.setHeader("Content-disposition", "attachment;filename=balancelog.xls");//默认Excel名称
//        wb.write(os);
//        os.flush();
//        os.close();
//    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("export")
    public String export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 查询数据库中的数据 List<Project>
        // List<Map<String,Object>>
        List<Map<String, Object>> list = createExcelRecord(createData());
        // 设置文件名称
        String fileName = "project数据";
        // 标题行名称
        String columnNames[] = { "编号", "姓名", "部门", "基本工资", "餐补", "车补", "房补", "医疗保险", "养老保险", "生育保险", "工伤保险", "失业保险", "公积金", "税金", "应发工资", "实发工资", "发放时间", "发放人"};// 列名
        // 需要和指定的key对应
        String keys[] = { "wageid", "username", "deptid", "deptid", "subsidy", "carellwance", "housingsubsidy", "medicalinsurance", "endowmentinsurance", "unemploymentinsurance", "birthinsurance", "employmentinjuryinsurance", "reservedfunds", "taxes", "netpayroll", "netpay", "wagedate", "issuer" };// map中的key

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    /**
     * 准备需要导出的数据
     */

    private List<Wage> createData() {
        List<Wage> wage = this.wageMapper.pageInfo();
        return wage;

    }

    /**
     * 将List中的对象转换成Map
     *
     * @param
     * @return
     */
    private List<Map<String, Object>> createExcelRecord(List<Wage> emp) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置数据也的名称
        map.put("sheetName", "项目数据");
        listmap.add(map);
        Wage wage = null;
        for (int j = 0; j < emp.size(); j++) {
            wage = emp.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("wageid", wage.getWageid());
            mapValue.put("username", wage.getUser().getUsername());
            mapValue.put("deptid", wage.getDeptid());
            mapValue.put("deptid", wage.getDeptid());
            mapValue.put("subsidy", wage.getWelfare().getSubsidy());
            mapValue.put("carellwance", wage.getWelfare().getCarallwance());
            mapValue.put("housingsubsidy", wage.getWelfare().getHousingsubsidy());
            mapValue.put("medicalinsurance", wage.getWelfare().getMedicallnsuranc());
            mapValue.put("endowmentinsurance", wage.getWelfare().getEndowmentinsurance());
            mapValue.put("unemploymentinsurance", wage.getWelfare().getUnemploymentinsurance());
            mapValue.put("birthinsurance", wage.getWelfare().getBirthinsurance());
            mapValue.put("employmentinjuryinsurance", wage.getWelfare().getEmploymentinjuryinsurance());
            mapValue.put("reservedfunds", wage.getWelfare().getReservedfunds());
            mapValue.put("taxes", wage.getTaxes());
            mapValue.put("netpay", wage.getNetpay());
            mapValue.put("netpayroll", wage.getNetpayroll());
            mapValue.put("wagedate", wage.getWagedate());
            mapValue.put("issuer", wage.getIssuer());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
