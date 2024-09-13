package com.nasr.twafq.color.controller;

import com.nasr.twafq.color.entity.Color;
import com.nasr.twafq.color.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable String id) {
        return colorService.getColorById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Color addColor(@RequestBody Color color) {
        return colorService.addColor(color);
    }

    @PostMapping("/all")
    public List<Color> setAllColors(@RequestBody List<Color> colors) {
        return colorService.setAllColors(colors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable String id, @RequestBody Color updatedColor) {
        try {
            return ResponseEntity.ok(colorService.updateColor(id, updatedColor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        colorService.deleteColor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hex/{hexCode}")
    public ResponseEntity<Color> getColorByHexCode(@PathVariable String hexCode) {
        Color color = colorService.getColorByHexCode(hexCode);
        if (color != null) {
            return ResponseEntity.ok(color);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
