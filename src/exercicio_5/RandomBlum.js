//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

var TestSuit = require('nist-randomness-test-suite');
var limit = 0;

// Função para verificar se dois números são coprimos
function areCoprime(a, b) {
    let smaller = Math.min(a, b);
    for (let i = 2; i <= smaller; i++) {
        if (a % i === 0 && b % i === 0) {
            return false;
        }
    }
    return true;
}

// Função para gerar números primos aleatórios
function generatePrime() {
    function isPrime(num) {
        for (let i = 2, sqrt = Math.sqrt(num); i <= sqrt; i++) {
            if (num % i === 0) return false;
        }
        return num > 1;
    }

    let prime = Math.floor(Math.random() * 1000000) + 1000000; 
    while (!isPrime(prime)) {
        prime = Math.floor(Math.random() * 1000000) + 1000000;
    }
    return prime;
}

// Função para executar os testes com um par de valores p e q
function runTests(p, q, s) {
    const n = p * q;
    let seed = s;
    var alpha = 0.005;
    var testSuite = new TestSuit(alpha);

    // Geração de bits
    function generateBits(numBits) {
        let bits = '';
        for (let i = 0; i < numBits; i++) {
            seed = (seed * seed) % n;
            const bit = seed % 2;
            bits += bit;
        }
        return bits;
    }

    // Testes na suite NIST (National Institute of Standards and Technology)
    function runNISTTests(bits) {
        const frequencyTestResult = testSuite.frequencyTest(bits);
        const runsTestResult = testSuite.runsTest(bits);
        const binaryMatrixRankTestResult = testSuite.binaryMatrixRankTest(bits);
        const nonOverlappingTemplateMatchingTestResult = testSuite.nonOverlappingTemplateMatchingTest(bits);

        console.log("Resultado do Teste de Frequência:", frequencyTestResult);
        console.log("Resultado do Teste de Sequências:", runsTestResult);
        console.log("Resultado do Teste de Rank de Matriz Binária:", binaryMatrixRankTestResult);
        console.log("Resultado do Teste de Correspondência de Modelo Não-Sobreposto:", nonOverlappingTemplateMatchingTestResult);

        // Verifica se todos os testes passaram
        return frequencyTestResult && runsTestResult && binaryMatrixRankTestResult && nonOverlappingTemplateMatchingTestResult;
    }

    let testsPassed = false;
    while (!testsPassed && limit < 100) {
        const newP = generatePrime();
        let newQ = generatePrime();
        while (newQ === newP || !areCoprime(newP, newQ)) {
            newQ = generatePrime();
        }
        console.log("Novos valores de p e q gerados:", newP, newQ);
        const generatedBits = generateBits(100000); // 10^5
        console.log("Executando testes NIST para p =", newP, ", q =", newQ, "e s =", s + ":");
        testsPassed = runNISTTests(generatedBits);
        limit= limit + 1;
    }
}

// Executa os testes
runTests(10007, 10037, 101); // Inicia com os valores fornecidos
