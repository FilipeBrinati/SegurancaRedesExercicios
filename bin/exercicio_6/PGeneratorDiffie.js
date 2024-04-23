//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

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

// Função para verificar se um número é primo
function isPrime(num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 === 0 || num % 3 === 0) return false;
    let i = 5;
    while (i * i <= num) {
        if (num % i === 0 || num % (i + 2) === 0) return false;
        i += 6;
    }
    return true;
}

// Função para encontrar uma raiz primitiva módulo p
function findPrimitiveRoot(p) {
    for (let g = 2; g < p; g++) {
        let isPrimitive = true;
        for (let i = 1; i <= p - 2; i++) {
            if (modPow(g, i, p) === 1) {
                isPrimitive = false;
                break;
            }
        }
        if (isPrimitive) {
            return g;
        }
    }
    return -1; // Não há raiz primitiva para este p
}

// Função para gerar um número primo com o número especificado de dígitos
function generatePrime(digits) {
    let min = Math.pow(10, digits - 1);
    let max = Math.pow(10, digits) - 1;
    let prime;
    do {
        prime = Math.floor(Math.random() * (max - min + 1)) + min;
        console.log(prime);
    } while (!isPrime(prime));
    return prime;
}

// Definindo o número de dígitos para o número primo p
//dependendo do número de dígitos pode demorar bastante para gerar tanto o primo
//quanto seu primitivo.
const numDigits = 8;
const p = generatePrime(numDigits);

// Calculando a raiz primitiva módulo p
const g = findPrimitiveRoot(p);

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
