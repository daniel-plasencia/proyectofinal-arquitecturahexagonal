package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.config;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarClienteUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cliente.EncontrarClienteUseCaseImpl;
import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {

    @Bean
    public EncontrarClienteUseCase encontrarClienteUseCase(ClienteRepositoryPort clienteRepository) {
        return new EncontrarClienteUseCaseImpl(clienteRepository);
    }

    /*@Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepository) {
        return new CreateUserUseCaseImpl(userRepository);
    }

    @Bean
    public FindUserUseCase findUserUseCase(UserRepositoryPort userRepository) {
        return new FindUserUseCaseImpl(userRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepositoryPort userRepository) {
        return new UpdateUserUseCaseImpl(userRepository);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryPort userRepository) {
        return new DeleteUserUseCaseImpl(userRepository);
    }*/

}
