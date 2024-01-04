ejecutarSumar = () => {
  let valor1 = recuperarEntero("txtValor1");
  let valor2 = recuperarEntero("txtValor2");
  let resultadoSuma = sumar(valor1, valor2);
  console.log(resultadoSuma);
};

ejecutarResta = () => {
  let valor1 = recuperarFloat("txtValor1");
  let valor2 = recuperarFloat("txtValor2");
  let resultadoResta = resta(valor1, valor2);
  console.log(resultadoResta);
};

sumar = (sum1, sum2) => {
  let resultado = sum1 + sum2;
  return resultado;
};

resta = (rest1, rest2) => {
  let resultado = rest1 - rest2;
  return resultado;
};

