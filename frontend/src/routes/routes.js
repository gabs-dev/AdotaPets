import React from 'react';
import { Route, Routes, BrowserRouter } from 'react-router-dom';

import FormLogin from '../components/Login/FormLogin';
import FormCadastroUsuario from '../components/Cadastro/FormCadastro';
import Home from '../Home';

const Rotas = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element = {<Home />} path="/" exact />
        <Route element = {<FormLogin />} path="/login" />
        <Route element = {<FormCadastroUsuario />} path="/criarConta" />
      </Routes>
    </BrowserRouter>
  );
}

export default Rotas;