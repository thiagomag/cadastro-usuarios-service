package br.com.postechfiap.cadastrousuarioservice.application.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
