package com.rae.creatingspace.utilities.data;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

import static com.rae.creatingspace.CreatingSpace.LOGGER;

import com.mojang.serialization.Codec;

public class AccessibilityMatrixReader {

    public static final Codec<AccessibilityParameter> ACCESSIBILITY_PARAMETER_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("deltaV").forGetter(AccessibilityParameter::deltaV),
                    Codec.INT.fieldOf("arrivalHeight").forGetter(AccessibilityParameter::arrivalHeight),
                    Codec.STRING.optionalFieldOf("modid", "").forGetter(AccessibilityParameter::modid), // Field for mod ID
                    Codec.STRING.optionalFieldOf("texture", "").forGetter(AccessibilityParameter::texture) // Field for texture location
            ).apply(instance, AccessibilityParameter::new)
    );

    public static final Codec<Map<String, Map<String, AccessibilityParameter>>> ACCESSIBILITY_MATRIX_CODEC =
            Codec.unboundedMap(Codec.STRING, Codec.unboundedMap(Codec.STRING, ACCESSIBILITY_PARAMETER_CODEC));

    public static final Codec<PartialAccessibilityMatrix> PARTIAL_ACCESSIBILITY_MATRIX_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.BOOL.optionalFieldOf("replace", false).forGetter(PartialAccessibilityMatrix::replace),
                    ACCESSIBILITY_MATRIX_CODEC.fieldOf("values").forGetter(PartialAccessibilityMatrix::partialMatrix)
            ).apply(instance, PartialAccessibilityMatrix::new)
    );

    public static final CodecJsonDataManager<PartialAccessibilityMatrix> MATRIX_HOLDER = new CodecJsonDataManager("creatingspace_utilities", PARTIAL_ACCESSIBILITY_MATRIX_CODEC, LOGGER);

    private static HashMap<ResourceKey<Level>, HashMap<ResourceKey<Level>, AccessibilityParameter>> accessibilityMatrix;

    public static HashMap<ResourceKey<Level>, HashMap<ResourceKey<Level>, AccessibilityParameter>> translator(Map<String, Map<String, AccessibilityParameter>> mapString) {
        HashMap<ResourceKey<Level>, HashMap<ResourceKey<Level>, AccessibilityParameter>> translatedMatrix = new HashMap<>();
        if (mapString != null) {
            for (String origin : mapString.keySet()) {
                HashMap<ResourceKey<Level>, AccessibilityParameter> accessibleDimensionsMap = new HashMap<>();
                for (String destination : mapString.get(origin).keySet()) {
                    AccessibilityParameter parameter = mapString.get(origin).get(destination);
                    ResourceKey<Level> destinationKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(destination));
                    accessibleDimensionsMap.put(destinationKey, parameter);
                }
                ResourceKey<Level> originKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(origin));
                translatedMatrix.put(originKey, accessibleDimensionsMap);
            }
        }
        accessibilityMatrix = translatedMatrix;
        return translatedMatrix;
    }

    // Method to fetch the texture location for a given dimension
    public static ResourceLocation getTextureForDimension(ResourceLocation dimensionKey) {
        if (accessibilityMatrix != null && accessibilityMatrix.containsKey(dimensionKey)) {
            AccessibilityParameter parameter = accessibilityMatrix.get(dimensionKey).get(dimensionKey);
            if (parameter != null) {
                return parameter.getTextureLocation();
            }
        }
        return new ResourceLocation("creatingspace", "textures/environment/moon.png"); // Fallback texture
    }

    public record PartialAccessibilityMatrix(boolean replace, Map<String, Map<String, AccessibilityParameter>> partialMatrix) {
    }

    public record AccessibilityParameter(int deltaV, int arrivalHeight, String modid, String texture) {
        public ResourceLocation getTextureLocation() {
            return new ResourceLocation(modid, texture);
        }
    }
}
