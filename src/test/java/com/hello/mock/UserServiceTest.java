package com.hello.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailClient emailClient;

    @Mock
    private SettingRepository settingRepository;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository, emailClient, settingRepository);
    }

    @Test
    public void register() {
        Mockito.lenient().when(settingRepository.isEnabled()).thenReturn(Boolean.TRUE);
        Mockito.lenient().when(userRepository.get()).thenReturn(new User("Marudhu", 36));
        User user = userService.register("Marudhu", 36);

        assertNotNull(user);
    }
}