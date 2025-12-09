# 🧪 Testes Automatizados de Front-end com Selenium

Projeto de avaliação da disciplina de Testes de Software, focado em testes automatizados para as funcionalidades de **Login** e **Cadastro de Produtos** utilizando Selenium WebDriver com Java.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Como Executar os Testes](#-como-executar-os-testes)
- [Casos de Teste Implementados](#-casos-de-teste-implementados)
- [Padrão Page Object](#-padrão-page-object)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

Este projeto implementa testes automatizados de front-end para um sistema web de controle de produtos. Os testes foram desenvolvidos seguindo as melhores práticas de automação, utilizando o padrão **Page Object Model (POM)** para organização e manutenibilidade do código.

### Funcionalidades Testadas

- ✅ **Realizar Login** - Validação de credenciais e acesso ao sistema
- ✅ **Cadastrar Produto** - Validação completa do formulário de cadastro

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 8+ | Linguagem de programação |
| Selenium WebDriver | 3.141.59 | Framework de automação web |
| JUnit | 4.13 | Framework de testes |
| Maven | 3.x | Gerenciador de dependências |
| ChromeDriver | 131+ | Driver do navegador Chrome |

---

## 📁 Estrutura do Projeto

```
prova-testes-software/
│
├── 📄 pom.xml                                    # Configuração Maven
├── 📄 README.md                                  # Documentação
├── 📊 Planilha_de_planejamento_de_testes.xlsx   # Casos de teste documentados
│
├── 📂 sistema/                                   # Aplicação web testada
│   ├── login.html
│   ├── produtos.html
│   └── src/
│       ├── css/
│       └── js/
│
└── 📂 src/test/
    ├── 📂 java/sistemadetestes/
    │   ├── 📂 pageObject/                        # Page Objects
    │   │   ├── BasePO.java                       # Classe base
    │   │   ├── LoginPO.java                      # Page Object do Login
    │   │   └── ProdutoPO.java                    # Page Object de Produtos
    │   │
    │   └── 📂 test/                              # Classes de Teste
    │       ├── BaseTest.java                     # Classe base dos testes
    │       ├── LoginTest.java                    # Testes de Login
    │       └── ProdutoTest.java                  # Testes de Produto
    │
    └── 📂 resources/
        └── chromedriver.exe                      # Driver do Chrome
```

---

## ⚙ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

1. **Java JDK 8 ou superior**
   ```bash
   java -version
   ```

2. **Maven**
   ```bash
   mvn -version
   ```

3. **Google Chrome** (versão atualizada)

4. **Eclipse IDE** (ou outra IDE de sua preferência)

---

## 🔧 Configuração do Ambiente

### 1. Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/prova-testes-software.git
cd prova-testes-software
```

### 2. Atualizar o ChromeDriver

O ChromeDriver deve ser compatível com a versão do seu Chrome:

1. Verifique sua versão do Chrome: `chrome://settings/help`
2. Baixe o ChromeDriver compatível: [Chrome for Testing](https://googlechromelabs.github.io/chrome-for-testing/)
3. Substitua o arquivo em `src/test/resources/chromedriver.exe`

### 3. Configurar o Caminho do Sistema

No arquivo `BaseTest.java`, ajuste o caminho para o seu ambiente:

```java
// Windows
private static final String URL_BASE = "file:///C:/caminho/do/projeto/sistema/login.html";

// Linux/Mac
private static final String URL_BASE = "file:///home/usuario/projeto/sistema/login.html";
```

### 4. Importar no Eclipse

1. File → Import → Maven → Existing Maven Projects
2. Selecione a pasta do projeto
3. Aguarde o Maven baixar as dependências

---

## ▶ Como Executar os Testes

### Via Eclipse IDE

1. Clique com botão direito na classe de teste (`ProdutoTest.java` ou `LoginTest.java`)
2. Run As → JUnit Test

### Via Maven (Terminal)

```bash
# Executar todos os testes
mvn test

# Executar apenas testes de Produto
mvn -Dtest=ProdutoTest test

# Executar apenas testes de Login
mvn -Dtest=LoginTest test
```

---

## 📝 Casos de Teste Implementados

### Testes de Login (7 casos)

| ID | Cenário | Resultado Esperado |
|----|---------|-------------------|
| TC001 | Email e senha vazios | Mensagem de erro |
| TC002 | Email preenchido, senha vazia | Mensagem de erro |
| TC003 | Email vazio, senha preenchida | Mensagem de erro |
| TC004-TC006 | Credenciais inválidas | Mensagem de erro |
| TC007 | Credenciais válidas | Acesso ao sistema |

### Testes de Produto (34 casos)

#### 🔴 Campos Obrigatórios (TC001-TC010)

| ID | Cenário | Resultado |
|----|---------|-----------|
| TC001 | Todos os campos vazios | ❌ Erro |
| TC002 | Apenas código | ❌ Erro |
| TC003 | Apenas nome | ❌ Erro |
| TC004 | Apenas quantidade | ❌ Erro |
| TC005 | Apenas valor | ❌ Erro |
| TC006 | Apenas data | ❌ Erro |
| TC007 | Sem código | ❌ Erro |
| TC008 | Sem nome | ❌ Erro |
| TC009 | Sem data | ❌ Erro |
| TC010 | Todos preenchidos | ✅ Sucesso |

#### 🟡 Validação de Dados (TC011-TC018)

| ID | Cenário |
|----|---------|
| TC011 | Quantidade com letras |
| TC012 | Quantidade negativa |
| TC013 | Valor com letras |
| TC014 | Valor negativo |
| TC015 | Código com caracteres especiais |
| TC016 | Nome com números |
| TC017 | Valor com vírgula |
| TC018 | Data futura |

#### 🟢 Limites/Boundary (TC019-TC024)

| ID | Cenário |
|----|---------|
| TC019 | Código muito longo (100 chars) |
| TC020 | Nome muito longo (200 chars) |
| TC021 | Quantidade zero |
| TC022 | Valor zero |
| TC023 | Quantidade muito grande |
| TC024 | Campos com espaços em branco |

#### 🔵 Interface/Usabilidade (TC025-TC028)

| ID | Cenário |
|----|---------|
| TC025 | Fechar modal com botão X |
| TC026 | Abrir modal múltiplas vezes |
| TC027 | Verificar título e campos do modal |
| TC028 | Botão Sair limpa campos |

#### 🟣 Tabela/Listagem (TC029-TC032)

| ID | Cenário |
|----|---------|
| TC029 | Cadastrar múltiplos produtos |
| TC030 | Verificar todas as colunas |
| TC031 | Tabela vazia inicialmente |
| TC032 | Verificar dados de linha específica |

#### ⚪ Navegação (TC033-TC034)

| ID | Cenário |
|----|---------|
| TC033 | Verificar título da página |
| TC034 | Botão Criar visível e habilitado |

---

## 🏗 Padrão Page Object

O projeto utiliza o padrão **Page Object Model (POM)** para:

- ✅ Separar a lógica de teste da estrutura das páginas
- ✅ Facilitar manutenção quando a UI muda
- ✅ Reutilizar código entre diferentes testes
- ✅ Melhorar legibilidade dos testes

### Exemplo de Uso

```java
// Page Object encapsula os elementos e ações
public class ProdutoPO extends BasePO {
    @FindBy(id = "codigo")
    public WebElement inputCodigo;
    
    public void executarAcaoDeCadastrar(String codigo, String nome, ...) {
        clicarBotaoCriar();
        preencherFormulario(codigo, nome, ...);
        clicarBotaoSalvar();
    }
}

// Teste fica limpo e legível
@Test
public void deveCadastrarProduto() {
    produtoPage.executarAcaoDeCadastrar("001", "Mouse", "10", "99.90", "2024-01-01");
    assertTrue(produtoPage.existeProdutoNaTabela());
}
```

---

## 📊 Documentação Adicional

- **Planilha de Testes**: `Planilha_de_planejamento_de_testes.xlsx`
  - Aba "Login": Casos de teste do login
  - Aba "Produto": 34 casos de teste documentados

---

## 👨‍💻 Autor

**Nome do Aluno**

- GitHub: [@seu-usuario](https://github.com/seu-usuario)

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da avaliação da disciplina de Testes de Software.

---

<p align="center">
  Feito com ☕ e Selenium
</p>
