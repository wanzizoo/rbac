package cn.wanzizoo.rbac.web.controller;

import cn.wanzizoo.rbac.domain.Department;
import cn.wanzizoo.rbac.query.QueryObject;
import cn.wanzizoo.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 6:26 下午
 * @description: 部门控制器
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;


    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", departmentService.query(qo));
        return "/department/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id, QueryObject qo) {
        if (null != id) {
            departmentService.delete(id);
        }
        return "redirect:/department/list.do?currentPage=" + qo.getCurrentPage();
    }

    @RequestMapping("/input")
    public String input(Model model, Long id, Integer currentPage) {
        if (null != id) {
            model.addAttribute("d", departmentService.get(id));
        }
        model.addAttribute("currentPage", currentPage);
        return "/department/input";
    }


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department d, Integer currentPage) {
        departmentService.saveOrUpdate(d);
        return "redirect:/department/list.do?currentPage=" + currentPage;
    }


}
