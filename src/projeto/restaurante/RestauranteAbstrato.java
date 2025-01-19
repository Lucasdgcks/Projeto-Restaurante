
package projeto.restaurante;

public abstract class RestauranteAbstrato {
    protected boolean aberto;

    public RestauranteAbstrato() {
        this.aberto = false;
    }

    public abstract void abrir();

    public abstract void fechar();

    public boolean isAberto() {
        return aberto;
    }
}
