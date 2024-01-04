//PROGRAMACION FUNCIONAL PARTE 2
ejecutarOperacion = (operar) => {
  let valor1 = recuperarEntero("txtValor1");
  let valor2 = recuperarEntero("txtValor2");
  let resultado = operar(valor1, valor2);
  console.log(resultado);
};

sumar = (sum1, sum2) => {
  let resultado = sum1 + sum2;
  return resultado;
};

resta = (rest1, rest2) => {
  let resultado = rest1 - rest2;
  return resultado;
};

//PROGRAMACION FUNCIONAL PARTE 1

// recibir una funcion ----- una funcion puede recibir como parametro otra funcion
ejecutar = (fn) => {
  console.log("EJECUTANDO FUNCIONES");
  fn(); //ejecutar la funcion
};

impirmir = () => {
  console.log("ESTOY IMPRIMIENDO");
};
saludo = () => {
  alert("APRENDIENDO PROGRAMACION FUNCIONAL");
};
testEjecutar = () => {
  ejecutar(impirmir);
  ejecutar(saludo);
  ejecutar(() => {
    alert("SOY UNA FUNCION ANONIMA");
  });
};
