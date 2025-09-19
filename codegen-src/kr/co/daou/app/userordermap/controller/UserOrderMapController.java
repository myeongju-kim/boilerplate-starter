package kr.co.daou.app.userordermap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.daou.app.userordermap.service.UserOrderMapService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-order-maps")
public class UserOrderMapController {

    private final UserOrderMapService userOrderMapService;

    //TODO: create your api here
}