package ir.piana.business.store.restmv;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.piana.business.store.service.store.StoreMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@Controller
//@RequestMapping()
public class StoreRest {

    @Autowired
    private StoreMenuService storeMenuService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping(path = "/test")
    public ModelAndView getTest(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "Users List - Custom tags");
        modelAndView.addObject("storeMenu", storeMenuService.getStoreMenus());
        return modelAndView;
    }

    @GetMapping(path = "/test2")
    public String getTest(Model model) {
        model.addAttribute("title", "Users List - Custom tags");
        return "test";
    }

    @GetMapping(path = "/home")
    public ModelAndView getHome(HttpServletRequest request) {
        return new ModelAndView("bulma.homePage");
    }

    @GetMapping(path = "/shop")
    public ModelAndView getShop(HttpServletRequest request) {
        return new ModelAndView("bulma.shopPage");
    }

    @GetMapping(path = "/gallery")
    public ModelAndView getGallery(HttpServletRequest request) {
        return new ModelAndView("bulma.galleryPage");
    }
}
