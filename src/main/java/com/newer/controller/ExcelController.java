package com.newer.controller;

import com.newer.dao.WageMapper;
import com.newer.domain.Wage;
import com.newer.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    public WageMapper wageMapper;
    /**
     * 导出excel
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        //response.reset();
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
