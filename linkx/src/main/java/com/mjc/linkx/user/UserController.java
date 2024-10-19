//package com.mjc.linkx.user;
//
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//
//@RequestMapping("/User")
//public class UserController {
//
//    private final IUserService userService;
//
//    @GetMapping("/")
//    public String home(Model model) { // 인증된 사용자의 정보를 보여줌
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // token에 저장되어 있는 인증된 사용자의 id 값
//
//        UserVo userVo = userService.getUserById(id);
//        userVo.setPassword(null); // password는 보이지 않도록 null로 설정
//        model.addAttribute("user", userVo);
//        return "home";
//    }
//
//    @GetMapping("/userList")
//    public String getUserList(Model model) { // User 테이블의 전체 정보를 보여줌
//        List<UserVo> userList = userService.getUserList();
//        model.addAttribute("list", userList);
//        return "userListPage";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() { // 로그인되지 않은 상태이면 로그인 페이지를, 로그인된 상태이면 home 페이지를 보여줌
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "loginPage";
//        return "redirect:/";
//    }
//
//    @GetMapping("/signup")
//    public String signupPage() {  // 회원 가입 페이지
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "signupPage";
//        return "redirect:/";
//    }
//
//    @PostMapping("/signup")
//    public String signup(
//            UserVo userVo) { // 회원 가입
//        try {
//            userService.signup(userVo);
//        } catch (DuplicateKeyException e) {
//            return "redirect:/signup?error_code=-1";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "redirect:/signup?error_code=-99";
//        }
//        return "redirect:/login";
//    }
//
//    @GetMapping("/update")
//    public String editPage(Model model) { // 회원 정보 수정 페이지
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserVo userVo = userService.getUserById(id);
//        model.addAttribute("user", userVo);
//        return "editPage";
//    }
//
//    @PostMapping("/update")
//    public String edit(UserVo userVo) { // 회원 정보 수정
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userVo.setId(id);
//        userService.edit(userVo);
//        return "redirect:/";
//    }
//
//    @PostMapping("/delete")
//    public String withdraw(HttpSession session) { // 회원 탈퇴
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (id != null) {
//            userService.withdraw(id);
//        }
//        SecurityContextHolder.clearContext(); // SecurityContextHolder에 남아있는 token 삭제
//        return "redirect:/";
//    }
//}
