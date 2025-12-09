package sistemadetestes.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object para a página de Cadastro de Produtos
 * Esta classe mapeia todos os elementos da tela de produtos e fornece métodos
 * para interagir com eles durante os testes automatizados
 */
public class ProdutoPO extends BasePO {

    // ==================== ELEMENTOS DO MODAL DE CADASTRO ====================
    
    /**
     * Campo de input para o código do produto
     */
    @FindBy(id = "codigo")
    public WebElement inputCodigo;
    
    /**
     * Campo de input para o nome do produto
     */
    @FindBy(id = "nome")
    public WebElement inputNome;
    
    /**
     * Campo de input para a quantidade do produto
     */
    @FindBy(id = "quantidade")
    public WebElement inputQuantidade;
    
    /**
     * Campo de input para o valor do produto
     */
    @FindBy(id = "valor")
    public WebElement inputValor;
    
    /**
     * Campo de input para a data de cadastro do produto
     */
    @FindBy(id = "data")
    public WebElement inputData;
    
    // ==================== BOTÕES ====================
    
    /**
     * Botão "Criar" que abre o modal de cadastro de produto
     */
    @FindBy(id = "btn-adicionar")
    public WebElement buttonCriar;
    
    /**
     * Botão "Salvar" dentro do modal de cadastro
     */
    @FindBy(id = "btn-salvar")
    public WebElement buttonSalvar;
    
    /**
     * Botão "Sair" dentro do modal de cadastro
     */
    @FindBy(id = "btn-sair")
    public WebElement buttonSair;
    
    // ==================== MENSAGENS ====================
    
    /**
     * Elemento span que exibe mensagens de erro/validação
     * Seletor CSS: #cadastro-produto .modal-body span#mensagem
     */
    @FindBy(id = "mensagem")
    public WebElement spanMensagem;
    
    /**
     * Div de alerta que contém a mensagem de erro
     */
    @FindBy(css = "#cadastro-produto .alert")
    public WebElement divAlerta;
    
    // ==================== TABELA ====================
    
    /**
     * Corpo da tabela onde os produtos são listados
     */
    @FindBy(css = "table tbody")
    public WebElement tabelaCorpo;
    
    /**
     * Primeira linha da tabela de produtos (após cadastro)
     */
    @FindBy(css = "table tbody tr:first-child")
    public WebElement primeiraLinhaTabelaProdutos;
    
    /**
     * Primeira coluna (código) da primeira linha da tabela
     */
    @FindBy(css = "table tbody tr:first-child td:nth-child(1)")
    public WebElement primeiraLinhaCodigo;
    
    /**
     * Segunda coluna (nome) da primeira linha da tabela
     */
    @FindBy(css = "table tbody tr:first-child td:nth-child(2)")
    public WebElement primeiraLinhaNome;

    // ==================== CONSTRUTOR ====================
    
    /**
     * Construtor padrão para criação de uma nova instância da página de produtos
     * @param driver Driver do navegador atual
     */
    public ProdutoPO(WebDriver driver) {
        super(driver);
    }

    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Método para escrever texto em um campo de input
     * Limpa o campo antes de escrever e pressiona TAB ao final
     * @param input Elemento de input onde será escrito
     * @param texto Texto a ser escrito no campo
     */
    public void escrever(WebElement input, String texto) {
        input.clear();
        input.sendKeys(texto + Keys.TAB);
    }
    
    /**
     * Obtém a mensagem de erro/validação exibida no modal
     * @return Texto da mensagem exibida
     */
    public String obterMensagem() {
        return this.spanMensagem.getText();
    }
    
    /**
     * Obtém o título da página atual
     * @return Título da página
     */
    public String obterTituloDaPagina() {
        return driver.getTitle();
    }
    
    /**
     * Clica no botão "Criar" para abrir o modal de cadastro de produto
     */
    public void clicarBotaoCriar() {
        buttonCriar.click();
    }
    
    /**
     * Clica no botão "Salvar" para tentar salvar o produto
     */
    public void clicarBotaoSalvar() {
        buttonSalvar.click();
    }
    
    /**
     * Clica no botão "Sair" para fechar o modal
     */
    public void clicarBotaoSair() {
        buttonSair.click();
    }
    
    /**
     * Verifica se a div de alerta está visível (não tem a classe 'esconder')
     * @return true se o alerta estiver visível, false caso contrário
     */
    public boolean alertaEstaVisivel() {
        String classes = divAlerta.getAttribute("class");
        return !classes.contains("esconder");
    }
    
    /**
     * Verifica se existe pelo menos um produto na tabela
     * @return true se existir pelo menos uma linha na tabela, false caso contrário
     */
    public boolean existeProdutoNaTabela() {
        String html = tabelaCorpo.getAttribute("innerHTML");
        return html.contains("<tr>");
    }
    
    /**
     * Obtém o código do primeiro produto na tabela
     * @return Código do primeiro produto
     */
    public String obterCodigoPrimeiroProduto() {
        return primeiraLinhaCodigo.getText();
    }
    
    /**
     * Obtém o nome do primeiro produto na tabela
     * @return Nome do primeiro produto
     */
    public String obterNomePrimeiroProduto() {
        return primeiraLinhaNome.getText();
    }

    // ==================== MÉTODOS DE AÇÃO COMPOSTOS ====================
    
    /**
     * Preenche todos os campos do formulário de cadastro de produto
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param quantidade Quantidade do produto
     * @param valor Valor do produto
     * @param data Data de cadastro do produto (formato: yyyy-MM-dd)
     */
    public void preencherFormulario(String codigo, String nome, String quantidade, String valor, String data) {
        escrever(inputCodigo, codigo);
        escrever(inputNome, nome);
        escrever(inputQuantidade, quantidade);
        escrever(inputValor, valor);
        escrever(inputData, data);
    }
    
    /**
     * Executa a ação completa de cadastrar um produto:
     * 1. Clica no botão Criar
     * 2. Preenche todos os campos
     * 3. Clica no botão Salvar
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param quantidade Quantidade do produto
     * @param valor Valor do produto
     * @param data Data de cadastro do produto
     */
    public void executarAcaoDeCadastrar(String codigo, String nome, String quantidade, String valor, String data) {
        clicarBotaoCriar();
        
        // Pequena pausa para garantir que o modal abriu
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        preencherFormulario(codigo, nome, quantidade, valor, data);
        clicarBotaoSalvar();
    }
}
