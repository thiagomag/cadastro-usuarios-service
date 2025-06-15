package br.com.postechfiap.autenticacaoservice.application.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
