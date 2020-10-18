package com.hello.mock;

public class UserService {

    private UserRepository userRepository;
    private EmailClient emailClient;
    private SettingRepository settingRepository;

    public UserService(UserRepository userRepository, EmailClient emailClient, SettingRepository settingRepository) {
        this.userRepository = userRepository;
        this.emailClient = emailClient;
        this.settingRepository = settingRepository;
    }

    public User register(String name, Integer age) {
        User user = new User(name, age);
        if (settingRepository.isEnabled()) {
            userRepository.save(user);
            Boolean emailStatus = emailClient.sendTo(user);
            System.out.println("Email Sent : " + emailStatus);
            MessageService messageService = new MessageService();
            Boolean sendStatus = messageService.sendMessage();
            System.out.println("Message Sent : " + sendStatus);
            return userRepository.get();
        }

        throw new UnsupportedOperationException("Disabled Registration");
    }
}
