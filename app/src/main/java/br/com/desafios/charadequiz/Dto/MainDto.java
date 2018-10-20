package br.com.desafios.charadequiz.Dto;

public class MainDto {
    private int respondidos;
    long tempoTotal;
    long tempoMedio;

    public MainDto(int respondidos, long tempoTotal, long tempoMedio) {
        this.respondidos = respondidos;
        this.tempoTotal = tempoTotal;
        this.tempoMedio = tempoMedio;
    }

    public long getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(long tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public long getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(long tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public int getRespondidos() {
        return respondidos;
    }

    public void setRespondidos(int respondidos) {
        this.respondidos = respondidos;
    }
}
