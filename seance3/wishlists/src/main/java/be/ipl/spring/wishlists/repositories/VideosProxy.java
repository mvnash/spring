package be.ipl.spring.wishlists.repositories;

import be.ipl.spring.wishlists.models.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "videos")
public interface VideosProxy {

    @GetMapping("/videos/{hash}")
    Video readOne(@PathVariable String hash);

}
