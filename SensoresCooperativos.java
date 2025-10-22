public class SensoresCooperativos {
    public static void main(String[] args){
      // Criação das threads com base em classes que implementam Runnable
        Thread sensorTemperatura = new Thread(new Sensor("Temperatura", -10,40));
        Thread sensorUmidade = new Thread(new Sensor("Umidade", 0, 100));
        Thread sensorPressao = new Thread(new Sensor("Pressão", 950, 1050));
        System.out.println("Iniciando monitoramento dos sensores...\n");
       // Inicia as threads
        sensorTemperatura.start();
        sensorUmidade.start();
        sensorPressao.start();
        
        try{  // Aguarda todas as threads terminarem (join)
            sensorTemperatura.join();
            sensorUmidade.join();
            sensorPressao.join();
        } catch (InterruptedException e) {
            System.out.println("A execução foi interrompida: " + e.getMessage());
        }

        System.out.println("\nMonitoramento encerrado. Todos os sensores finalizaram suas leituras.");

    }
}

class Sensor implements Runnable {// Classe Sensor que implementa Runnable
    private final String tipo;
    private final double min;
    private final double max;

    public Sensor(String tipo, double min, double max){
        this.tipo = tipo;
        this.min = min;
        this.max = max;
    }

    public void run(){ // Simula 5 leituras do sensor
        
        for (int i = 1; i <= 5; i++){
            double leitura = gerarLeitura();
            System.out.printf("[%s] Leitura %d: %.2f%n", tipo, i, leitura);

            try {
                Thread.sleep(2000); // aguarda 2 segundos entre as leituras
            } catch (InterruptedException e) {
                System.out.println("sensor " + tipo + " interrompido!");
                return;
            }
        }

        System.out.println(">> sensor " + tipo + "finalizado.\n");
    }
    // Método para gerar uma leitura aleatória dentro do intervalo definido
    private double gerarLeitura(){
        return min + Math.random() * (max - min);
    }
 }
