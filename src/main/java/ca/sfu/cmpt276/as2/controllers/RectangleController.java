package ca.sfu.cmpt276.as2.controllers;

import ca.sfu.cmpt276.as2.models.Rectangle;
import ca.sfu.cmpt276.as2.models.RectangleRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Controller
public class RectangleController {
    @Autowired
    private RectangleRepository rectangleRepo;

    // TODO remove later
    @RestController
    public class API {
        @GetMapping("/api/rectangles")
        public List<Rectangle> getAllRectanglesAPI(Model model) {

            return rectangleRepo.findAll();
        }
    }

    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        List<Rectangle> rectangles = rectangleRepo.findAll();
        model.addAttribute("rectList", rectangles);

        return "rectangle/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Rectangle newRectangle, HttpServletResponse response) {
        return "";
    }
}