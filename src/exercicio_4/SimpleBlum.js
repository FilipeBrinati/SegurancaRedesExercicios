var TestSuit = require('nist-randomness-test-suite');

// Valores de p e q fornecidos
const pqsPairs = [
    { p: 10007, q: 10037, s:101 },
    { p: 15647, q: 17923, s:101}, //Todos os resultados verdadeiros para 10^4 bits
];

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
    }

    const generatedBits = generateBits(10000000);// 10^7
    //console.log("Sequência de bits gerada para p =", p, "e q =", q + ":", generatedBits);

    // Executar testes NIST
    console.log("Executando testes NIST para p =", p, ", q =", q, "e s =", s + ":");
    runNISTTests(generatedBits);
}

// Iterar sobre todos os pares de valores p e q e executar os testes
pqsPairs.forEach(pair => {
    runTests(pair.p, pair.q, pair.s);
});
