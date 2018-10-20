package br.com.desafios.charadequiz.Dto;

import android.widget.TextView;

public class ResumeDto {
    long esperado;
    long    total;
    long medio;
    int    acertos;
    int erros;

    public ResumeDto(long esperado, long total, long medio, int acertos, int erros) {
        this.esperado = esperado;
        this.total = total;
        this.medio = medio;
        this.acertos = acertos;
        this.erros = erros;
    }

    public long getEsperado() {
        return esperado;
    }

    public void setEsperado(long esperado) {
        this.esperado = esperado;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getMedio() {
        return medio;
    }

    public void setMedio(long medio) {
        this.medio = medio;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
}
