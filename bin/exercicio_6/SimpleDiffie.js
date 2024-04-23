// Função para calcular a potência modular (a^b mod m)
function modPow(a, b, m) {
    let result = 1;
    while (b > 0) {
        if (b % 2 === 1) {
            result = (result * a) % m;
        }
        a = (a * a) % m;
        b = Math.floor(b / 2);
    }
    return result;
}

// Definindo valores fixos
const p = 104729; // Maior numero primo do exercicio 5
//BigInt(2) ** BigInt(77232917) - BigInt(1); número primo de Mersenne ... Meu note não aguentou, exagerei no grande
const g = 12; // Raiz primitiva módulo p

console.log('p:', p);
console.log('g:', g);

const a = 284127348; 
const b = 110295234; 

console.log('a:', a);
console.log('b:', b);

// Calcula A = g^a mod p
const A = modPow(g, a, p);

// Calcula B = g^b mod p
const B = modPow(g, b, p);

console.log('A:', A);
console.log('B:', B);

// Ambos calculam a chave secreta
const secretKeyAna = modPow(B, a, p);
const secretKeyJose = modPow(A, b, p);

console.log('Chave secreta de Ana:', secretKeyAna);
console.log('Chave secreta de Jose:', secretKeyJose);
