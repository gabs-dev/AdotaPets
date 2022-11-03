import React from 'react';
import { Link } from 'react-router-dom';

import NavBar from './components/Navbar/NavBar';
import FormCadastrarPet from './components/Pet/FormCadastrarPet'

const Home = () => {
  return (
    <div>
      <NavBar />
      <FormCadastrarPet />
    </div>
  );
}

export default Home;