package ca.sfu.cmpt276.as2.controllers;

import ca.sfu.cmpt276.as2.models.Rectangle;
import ca.sfu.cmpt276.as2.models.RectangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RectangleController {
    @Autowired
    private RectangleRepository rectangleRepo;

    @GetMapping("/api/rectangle")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all rectangles...");

        List<Rectangle> rectangles = rectangleRepo.findAll();

        model.addAttribute("rect", rectangles);

        return null;
    }
}