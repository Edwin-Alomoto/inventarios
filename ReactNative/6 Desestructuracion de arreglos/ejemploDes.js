recuperar = () => {
  let frutas = ["pera", "manzanas", "sandia"];
  frutas.push("banana");
  return frutas;
};

testRecuperar = () => {
  let misfrutas = recuperar();
  let frutaPrimera = misfrutas[0];
  let otraFruta = misFrutas[1];
  console.log("1>>>>>" + frutaPrimera);
  console.log("2>>>>>" + otraFruta);
};

testRecuperarDes = () => {
  let [primeraFruta, segundaFruta] = recuperar();
  console.log("1>>>>>>" + primeraFruta);
  console.log("2>>>>>>" + segundaFruta);
};
