package ca.sfu.cmpt276.as2.controllers;

import ca.sfu.cmpt276.as2.models.Rectangle;
import ca.sfu.cmpt276.as2.models.RectangleRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/rectangles")
    public String getAllRectangles(HttpServletResponse response, Model model) {
        List<Rectangle> rectangles = rectangleRepo.findAll(Sort.by(Sort.Direction.ASC, "uid"));

        model.addAttribute("rectList", rectangles);
        response.setStatus(200);

        return "rectangle/showAll";
    }

    @PostMapping("/rectangles")
    public String addRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response, Model model) {
        try {
            String newName = newRectangle.get("name");
            double newWidth = Double.parseDouble(newRectangle.get("width"));
            double newHeight = Double.parseDouble(newRectangle.get("height"));
            String newColor = newRectangle.get("color");
            String newMaterial = newRectangle.get("material");
            int newDurability = Integer.parseInt(newRectangle.get("durability"));
            int newRarity = new Random().nextInt(5) + 1;

            rectangleRepo.save(new Rectangle(newName, newWidth, newHeight, newColor, newMaterial, newDurability, newRarity));

        } catch (Exception e) {
            model.addAttribute("message", "Invalid inputs");
            response.setStatus(500);

            return "rectangle/error";
        }

        model.addAttribute("message", "Successfully added rectangle with name: " + newRectangle.get("name"));
        response.setStatus(201);

        return "rectangle/addedRectangle";
    }

    @GetMapping("/rectangles/{id}")
    public String getIndividualRectangle(@PathVariable int id, HttpServletResponse response, Model model) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);

        if (rectangleOptional.isEmpty()) {
            model.addAttribute("message", "Rectangle with id: " + id + " does not exist");
            response.setStatus(404);

            return "rectangle/error";
        }

        model.addAttribute("rect", rectangleOptional.get());
        response.setStatus(200);

        return "rectangle/showOne";
    }


    @DeleteMapping("/rectangles/{id}")
    public String deleteRectangles(@PathVariable int id, HttpServletResponse response, Model model) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);

        if (rectangleOptional.isEmpty()) {
            model.addAttribute("message", "Failed to delete!");
            response.setStatus(404);
            return "rectangle/error";
        }

        rectangleRepo.deleteById(rectangleOptional.get().getUid());
        model.addAttribute("message", "Successfully deleted rectangle with id: " + id);
        response.setStatus(200);

        return "rectangle/deletedRectangle";
    }

    @PutMapping("/rectangles/{id}")
    public String updateRectangles(@PathVariable int id, @RequestParam Map<String, String> updatedRectangle, HttpServletResponse response, Model model) {
        Optional<Rectangle> rectangleOptional = rectangleRepo.findById(id);
        try {
            if (rectangleOptional.isPresent()) {
                Rectangle rectangle = rectangleOptional.get();
                rectangle.setName(updatedRectangle.get("name"));
                rectangle.setWidth(Double.parseDouble(updatedRectangle.get("width")));
                rectangle.setHeight(Double.parseDouble(updatedRectangle.get("height")));
                rectangle.setColor(updatedRectangle.get("color"));
                rectangle.setMaterial(updatedRectangle.get("material"));
                rectangle.setDurability(Integer.parseInt(updatedRectangle.get("durability")));

                rectangleRepo.save(rectangle);
            }
        } catch (Exception e) {
            model.addAttribute("message", "Invalid inputs");
            response.setStatus(500);

            return "rectangle/error";
        }

        model.addAttribute("message", "Successfully updated rectangle with id: " + id);
        response.setStatus(201);

        return "rectangle/updatedRectangle";
    }
}