import * as React from 'react';
import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
/*import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';*/
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Copyright from '../Copyright';
import CatBackground from '../../assets/images/cat.png';
import PageTitle from '../../hooks/PageTitle';
import api from "../../api/api";
import Snackbar from '@mui/material/Snackbar';
import Slide from '@mui/material/Slide';
import { Alert } from '@mui/material';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

const theme = createTheme();

function FormCadastro() {
    const headers = {
        'Content-Type': 'application/json'
    };

    const snackbarProps = {
        open: false,
        severity: "info",
        message: '',
    }

    const userData = {
        nome: '',
        email: '',
        senha: '',
        cnpj: ''
    };

    const [user, setUser] = useState(userData);
    const [snackbar, setSnackbar] = useState(snackbarProps);
    const [confirmarSenha, setConfirmarSenha] = useState();

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
        if (confirmarSenha != user.senha) {
            configureSnackbar(0, 'Senhas diferentes!');
            return;
        }
        console.log(JSON.stringify(user));
        await api
            .put("/usuario", JSON.stringify(user), {
                headers: headers
            })
            .then(response => {
                configureSnackbar(response.status);
                console.log(response.status)
            })
            .catch(err =>
                configureSnackbar(err.response.status)
            )
    };

    const configureSnackbar = (status, mensagem) => {
        snackbarProps.open = true;
        if (status === 201) {
            snackbarProps.message = 'Cadastro realizado com sucesso!';
            snackbarProps.severity = 'success';
        } else if (status === 0) {
            snackbarProps.message = mensagem;
            snackbarProps.severity = 'error';
        } else {
            snackbarProps.message = 'Erro! Não foi possivel cadastrar o usuário.';
            snackbarProps.severity = 'error';
        }

        handleOpen(snackbarProps);
    }


    /*
        const handleSubmit = (event) => {
            event.preventDefault();
            const data = new FormData(event.currentTarget);
            console.log({
                email: data.get('email'),
                password: data.get('password'),
            });
        };
    
        const [cgcNumber, setCgcNumber] = React.useState('');
    
        const handleChange = (event) => {
            setCgcNumber(event.target.value);
        };
    */


    PageTitle("Criar conta - AdotaPets")
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
                        backgroundImage: `url(${CatBackground})`,
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
                            Crie a sua conta
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="nome"
                                label="Nome"
                                name="nome"
                                autoComplete="nome"
                                autoFocus
                                onChange={handleChange}
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Email"
                                name="email"
                                type="email"
                                autoComplete="email"
                                autoFocus
                                onChange={handleChange}
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
                                onChange={handleChange}
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="confirmarSenha"
                                label="Confirmar Senha"
                                type="password"
                                id="confirmarSenha"
                                autoComplete="current-password"
                                onChange={(e) => setConfirmarSenha(e.target.value)}
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="cnpj"
                                label="CNPJ/CPF"
                                name="cnpj"
                                autoComplete="cnpj"
                                autoFocus
                                onChange={handleChange}
                            />

                            <FormControl fullWidth style={{ display: 'none' }}>
                                <InputLabel id="demo-simple-select-label">Tipo Usuário</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                //value={cgcNumber}
                                //label="Age"
                                //onChange={handleChange}
                                >
                                    <MenuItem value={10}>Pessoa Física</MenuItem>
                                    <MenuItem value={20}>Pessoa Jurídica </MenuItem>
                                </Select>
                            </FormControl>

                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                            >
                                Criar Conta
                            </Button>
                            <Copyright sx={{ mt: 5 }} />
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}
export default FormCadastro;
