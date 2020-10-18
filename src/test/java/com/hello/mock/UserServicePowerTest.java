package com.hello.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = {"com.hello.mock.*"})
public class UserServicePowerTest {

    private UserRepository mockUserRepository = mock(UserRepository.class);
    private EmailClient mockEmailClient = mock(EmailClient.class);
    private SettingRepository mockSettingRepository = mock(SettingRepository.class);
    private MessageService mockMessageService = mock(MessageService.class);

    private UserService userService = new UserService(mockUserRepository, mockEmailClient, mockSettingRepository);

    @Test
    public void testRegister() throws Exception {
        when(mockSettingRepository.isEnabled()).thenReturn(Boolean.TRUE);
        whenNew(MessageService.class).withNoArguments().thenReturn(mockMessageService);
        when(mockMessageService.sendMessage()).thenReturn(Boolean.FALSE);

        User registeredUser = userService.register("Marudhu", 36);

        System.out.println("Registered User : " + registeredUser);
    }
}
