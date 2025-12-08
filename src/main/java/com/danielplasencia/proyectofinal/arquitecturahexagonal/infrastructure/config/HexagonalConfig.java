package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.config;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.*;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.NotificacionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cliente.EncontrarClienteUseCaseImpl;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cuenta.ConsultarSaldoUseCaseImpl;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cuenta.CrearCuentaUseCaseImpl;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cuenta.EncontrarCuentaUseCaseImpl;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.transaccion.EncontrarTransaccionUseCaseImpl;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.transaccion.TransferirDineroUseCaseImpl;
import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {

    @Bean
    public EncontrarClienteUseCase encontrarClienteUseCase(ClienteRepositoryPort clienteRepository) {
        return new EncontrarClienteUseCaseImpl(clienteRepository);
    }

    @Bean
    public ConsultarSaldoUseCase consultarSaldoUseCase(CuentaRepositoryPort cuentaRepository) {
        return new ConsultarSaldoUseCaseImpl(cuentaRepository);
    }

    @Bean
    public CrearCuentaUseCase crearCuentaUseCase(CuentaRepositoryPort cuentaRepository, ClienteRepositoryPort clienteRepository) {
        return new CrearCuentaUseCaseImpl(cuentaRepository, clienteRepository);
    }

    @Bean
    public EncontrarCuentaUseCase encontrarCuentaUseCase(CuentaRepositoryPort cuentaRepository) {
        return new EncontrarCuentaUseCaseImpl(cuentaRepository);
    }

    @Bean
    public TransferirDineroUseCase transferirDineroUseCase(CuentaRepositoryPort cuentaRepository, TransaccionRepositoryPort transaccionRepository, NotificacionRepositoryPort notificacionRepository) {
        return new TransferirDineroUseCaseImpl(cuentaRepository, transaccionRepository, notificacionRepository);
    }

    @Bean
    public EncontrarTransaccionUseCase encontrarTransaccionUseCase(TransaccionRepositoryPort transaccionRepository) {
        return new EncontrarTransaccionUseCaseImpl(transaccionRepository);
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
