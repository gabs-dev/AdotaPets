import * as React from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Snackbar from '@mui/material/Snackbar';
import Slide from '@mui/material/Slide';
import { Alert } from '@mui/material';

import Copyright from '../Copyright';
import DogBackground from '../../assets/images/dog.png';
import PageTitle from '../../hooks/PageTitle';

import api from "../../api/api";

const theme = createTheme();

export default function FormLogin() {
  const headers = {
    'Content-Type': 'application/json'
  };

  const snackbarProps = {
    open: false,
    severity: "info",
    message: '',
  }

  const userData = {
    email: '',
    senha: ''
  };

  const [user, setUser] = useState(userData);
  const [snackbar, setSnackbar] = useState(snackbarProps);

  const handleClose = (event, reason) => {
    if (reason === 'clickaway')
      return;
    
    snackbarProps.open = false;
    setSnackbar(snackbarProps)
  }

  const handleOpen = (snackbarPro) => {
    setSnackbar(snackbarProps);
  }

  const handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;

    setUser({ ...user, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    await api
      .post("/login", JSON.stringify(user), {
        headers: headers
      })
      .then(response => {
        configureSnackbar(response.status);
      })
      .catch(err => 
        configureSnackbar(err.response.status)
      )
  };

  const configureSnackbar = (status) => {
    snackbarProps.open = true;
    if (status === 200) {
      snackbarProps.message = 'Login realizado com sucesso!';
      snackbarProps.severity = 'success';
    } else {
      snackbarProps.message = 'Erro! Usuário ou senha inválidos.';
      snackbarProps.severity = 'error';
    }

    handleOpen(snackbarProps);
  }

  PageTitle("Login - AdotaPets")
  return (
    <ThemeProvider theme={theme}>
      <Snackbar
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        autoHideDuration={3000}
        open={snackbar.open}
        onClose={handleClose}
        TransitionComponent={Slide}
      >
        <Alert
          onClose={handleClose}
          severity={snackbar.severity}
          sx={{ width: '100%' }}
          variant="filled"
        >
          {snackbar.message}
        </Alert>
      </Snackbar>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${DogBackground})`,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Faça o login
            </Typography>
            <Box component="form" noValidate={false} onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Usuário"
                name="email"
                autoComplete="email"
                type="email"
                autoFocus
                onChange = {handleChange}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="senha"
                label="Senha"
                type="password"
                id="senha"
                autoComplete="current-password"
                onChange = {handleChange}
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                style={{ backgroundColor: "#0288D1"}}
              >
                Login
              </Button>
              <Grid container style={{ textAlign: 'left' }}>
                <Grid item xs>
                  <Link to="#" variant="body2">
                    Esqueceu sua senha?
                  </Link>
                </Grid>
                <Grid item>
                  <Link to="/criarConta" variant="body2">
                    {"Não possui conta? Clique aqui"}
                  </Link>
                </Grid>
              </Grid>
              <Copyright sx={{ mt: 5 }} />
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}