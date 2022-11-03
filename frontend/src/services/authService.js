const authService = {
  
  // Função para salvar o usuário logado no local storage
  setLoggedUser(user) {
    let parseData = JSON.stringify(user);
    localStorage.setItem("user", parseData);
  },

  getLoggedUser() {
    let user = localStorage.getItem("user");
    if (!user) return null;
    try {
      let parseData = JSON.parse(user);
      return parseData;
    } catch (error) {
      console.log(error);
      return null;
    }
  },

  cleanLoggedUser() {
    localStorage.clear();
  }

}

export default authService;