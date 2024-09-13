package com.nasr.twafq.color.service;

import com.nasr.twafq.color.entity.Color;
import com.nasr.twafq.color.repo.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    public List<Color> setAllColors(List<Color> colors) {
        return colorRepository.saveAll(colors);
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(String id) {
        return colorRepository.findById(id);
    }

    public Color addColor(Color color) {
        return colorRepository.save(color);
    }

    public Color updateColor(String id, Color updatedColor) {
        return colorRepository.findById(id)
                .map(color -> {
                    color.setHexCode(updatedColor.getHexCode());
                    // Index is not updated here to avoid conflicts
                    return colorRepository.save(color);
                }).orElseThrow(() -> new RuntimeException("Color not found"));
    }

    public void deleteColor(String id) {
        colorRepository.deleteById(id);
    }

    public Color getColorByHexCode(String hexCode) {
        return colorRepository.findByHexCode(hexCode);
    }

    // Utility method to get the next available index
    private Integer getNextAvailableIndex() {
        List<Color> colors = colorRepository.findAll();
        return colors.size();
    }
}
