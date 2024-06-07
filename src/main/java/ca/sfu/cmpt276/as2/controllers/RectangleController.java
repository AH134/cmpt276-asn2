package ca.sfu.cmpt276.as2.controllers;

import ca.sfu.cmpt276.as2.models.Rectangle;
import ca.sfu.cmpt276.as2.models.RectangleRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Controller
public class RectangleController {
    @Autowired
    private RectangleRepository rectangleRepo;

    // TODO remove later
    @RestController
    public class API {
        @GetMapping("/api/rectangles")
        public List<Rectangle> getAllRectanglesAPI() {
            return rectangleRepo.findAll();
        }

        @GetMapping("api/rectangles/{id}")
        public Rectangle getRectangleByIdAPI(@PathVariable int id) {
            Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);
            return rectangleOptional.orElse(null);
        }

        @PostMapping("api/rectangles/color")
        public String getRectangleColorByIdAPI(@RequestParam Map<String, String> req) {
            return req.get("color");
        }
    }

    @GetMapping("/rectangles")
    public String getAllRectangles(Model model) {
        List<Rectangle> rectangles = rectangleRepo.findAll();
        model.addAttribute("rectList", rectangles);

        return "rectangle/showAll";
    }

    @PostMapping("/rectangles")
    public String addRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response) {
        String newName = newRectangle.get("name");
        double newWidth = Double.parseDouble(newRectangle.get("width"));
        double newHeight = Double.parseDouble(newRectangle.get("height"));
        String newColor = newRectangle.get("color");
        String newMaterial = newRectangle.get("material");
        int newDurability = Integer.parseInt(newRectangle.get("durability"));
        int newRarity = new Random().nextInt(5) + 1;

        rectangleRepo.save(new Rectangle(newName, newWidth, newHeight, newColor, newMaterial, newDurability, newRarity));
        response.setStatus(201);

        // temp redirect
        return "rectangle/addedRectangle";
    }

    @GetMapping("/rectangles/{id}")
    public String getIndividualRectangle(@PathVariable int id, HttpServletResponse response, Model model) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);

        if (rectangleOptional.isPresent()) {
            model.addAttribute("rect", rectangleOptional.get());
            response.setStatus(200);
            return "rectangle/showOne";
        } else {
            // TODO make another page for error
            model.addAttribute("message", "Failed to delete!");
            response.setStatus(404);
            return "rectangle/addedRectangle";
        }
    }


    @DeleteMapping("/rectangles/{id}")
    public String deleteRectangles(@PathVariable int id, HttpServletResponse response, Model model) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);

        if (rectangleOptional.isPresent()) {
            rectangleRepo.deleteById(rectangleOptional.get().getUid());
            model.addAttribute("message", "Successfully deleted!");
            response.setStatus(200);
        } else {
            model.addAttribute("message", "Failed to delete!");
            response.setStatus(404);
        }

        return "rectangle/deletedRectangle";
    }

    @PutMapping("/rectangles/{id}")
    public String updateRectangles(@PathVariable int id, HttpServletResponse response) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);
        System.out.println(rectangleOptional.orElse(null));

        // temp redirect
        return "rectangle/updatedRectangle";
    }
}