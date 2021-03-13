package com.apd.tema2.factory;

import com.apd.tema2.intersections.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Factory: va puteti crea cate o instanta din fiecare tip de implementare de Intersection.
 */
public class IntersectionFactory {
    private static Map<String, SimpleIntersection> cache = new HashMap<>();

    static {
        cache.put("simple_semaphore",new SimpleIntersection(){


        });
        cache.put("simple_n_roundabout",new SimpleIntersection(){

        });
        cache.put("simple_strict_1_car_roundabout",new SimpleIntersection(){

        });
        cache.put("simple_strict_x_car_roundabout",new SimpleIntersection(){

        });
        cache.put("simple_max_x_car_roundabout",new SimpleIntersection(){

        });
        cache.put("priority_intersection",new SimpleIntersection(){

        });
        cache.put("crosswalk",new SimpleIntersection(){

        });
        cache.put("simple_maintenance",new SimpleIntersection(){

        });
        cache.put("complex_maintenance",new SimpleIntersection(){

        });
        cache.put("railroad",new SimpleIntersection(){

        });
        // cache.put("name_intersection", new Intersection() {
        // });
    }

    public static SimpleIntersection getIntersection(String handlerType) {
        return cache.get(handlerType);
    }

}
