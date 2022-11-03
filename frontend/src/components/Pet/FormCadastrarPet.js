import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Grid from '@mui/material/Grid';
import Slide from '@mui/material/Slide';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormLabel from '@mui/material/FormLabel';
import Switch from '@mui/material/Switch';
import FormGroup from '@mui/material/FormGroup';
import { styled } from '@mui/material/styles';
import AddPhotoIcon from '@mui/icons-material/AddPhotoAlternate';
import PetsIcon from '@mui/icons-material/Pets';
import Snackbar from '@mui/material/Snackbar';
import { Alert } from '@mui/material';

import especiesPet from '../../utils/especiesPet';
import authService from '../../services/authService';
import api from "../../api/api";

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

const ColorButton = styled(Button)(({ theme }) => ({
  color: theme.palette.getContrastText('#039be5'),
  backgroundColor: '#039be5',
  '&:hover': {
    backgroundColor: '#0277bd',
  },
}));

export default function FormCadastrarPet(openDialog) {
  const headers = {
    'Content-Type': 'application/json'
  };

  const snackbarProps = {
    open: false,
    severity: "info",
    message: '',
  }

  const petData = {
    nome: '',
    raca: '',
    tipoPet: '',
    peso: 0,
    idade: '',
    sexo: 0,
    vacinado: false,
    castrado: false,
    informacoesAdicionais: '',
    responsavelId: 0
  };

  const [open, setOpen] = React.useState(false);
  const [pet, setPet] = React.useState(petData);
  const [snackbar, setSnackbar] = React.useState(snackbarProps);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = (event, reason) => {
    if (reason === "escapeKeyDown" || reason === "backdropClick")
      return;
    setOpen(false);
  };

  const handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;

    setPet({ ...pet, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    converterDadosPet();
    setResponsavelId();
    await api
      .put("/pet", JSON.stringify(pet), { 
        headers: headers
      })
      .then(response => {
        configureSnackbar(response.status);
      })
      .catch(err => {
        configureSnackbar(err.response.status);
      })
  }

  const handleCloseSnackbar = (event, reason) => {
    if (reason === 'clickaway')
            return;

    snackbarProps.open = false;
    setSnackbar(snackbarProps);
  }

  const handleOpenSnackbar = () => {
    setSnackbar(snackbarProps);
  }

  const converterDadosPet = () => {
    pet.vacinado = pet.vacinado === "on" ? true : false;
    pet.castrado = pet.castrado === "on" ? true : false;

    pet.peso = parseFloat(pet.peso);
    pet.sexo = parseInt(pet.sexo);
  }

  const configureSnackbar = (status) => {
    snackbarProps.open = true;
    if (status === 201) {
        snackbarProps.message = 'Pet cadastrado sucesso!';
        snackbarProps.severity = 'success';
        handleClose();
        //window.location.reload(true);
    } else {
        snackbarProps.message = 'Erro! Não foi possível cadastrar o pet.';
        snackbarProps.severity = 'error';
    }

    handleOpenSnackbar(snackbarProps);
  }

  const setResponsavelId = () => {
    pet.responsavelId = authService.getLoggedUser().id;
  }

  return (
    <div>
      <Snackbar
          anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
          autoHideDuration={3000}
          open={snackbar.open}
          onClose={handleCloseSnackbar}
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
      <Fab color="primary" aria-label="add" onClick={handleClickOpen} 
      sx={{ 
        boxShadow: 4,
        position: "fixed",
        bottom: (theme) => theme.spacing(2),
        right: (theme) => theme.spacing(2)
      }}>
        <AddIcon />
      </Fab>
      <Dialog 
        open={open}
        TransitionComponent={Transition} 
        onClose={handleClose}
        disableEscapeKeyDown={false}
      >
        <DialogTitle
          sx={{
            boxShadow: 1,
            backgroundColor: '#039be5'
           }}
        >
          <Typography 
            align="center"
            component="h1"
            variant="h5"
            sx={{
              mr: 2,
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: '#FFFF',
              textDecoration: 'none',
              alignContent: 'center',
            }}
          >
            Dados do Pet
          </Typography>
        </DialogTitle>
        <DialogContent>
          <Box
            sx={{
              marginTop: 2,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Box component="form" noValidate={false} autoComplete="off" onSubmit={handleSubmit}>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={12}>
                  <TextField
                    name="nome"
                    fullWidth
                    id="nome"
                    label="Nome do Pet"
                    autoFocus
                    autoComplete="off"
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs={12} sm={12}>
                  <TextField
                    fullWidth
                    name="raca"
                    label="Raça"
                    id="raca"
                    onChange={handleChange}
                  />
                </Grid>
              </Grid>
              <Grid item xs={12} sm={12} sx={{ mt: 2, mb: 2}}>
                <FormControl fullWidth>
                  <InputLabel id="especie-select-label">Tipo de Pet</InputLabel>
                  <Select
                    labelId="especie-select-label"
                    id="tipoPet"
                    required
                    value={pet.tipoPet}
                    name="tipoPet"
                    label="Tipo de Pet"
                    onChange={handleChange}
                  >
                    {especiesPet.especies().map(especie => {
                      return <MenuItem key={especie.id} value={parseInt(especie.id)}>{especie.name}</MenuItem>
                    })}
                  </Select>
                </FormControl>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    name="peso"
                    fullWidth
                    id="peso"
                    label="Peso"
                    type="number"
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    name="idade"
                    label="Idade"
                    type="text"
                    id="idade"
                    onChange={handleChange}
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6} sx={{ mt: 2 }}>
                  <FormControl>
                    <FormLabel id="controlled-radio-buttons-group-sexo">Sexo</FormLabel>
                    <RadioGroup
                      aria-labelledby="controlled-radio-buttons-group-sexo"
                      name="sexo"
                      //value={value}
                      onChange={handleChange}
                    >
                      <FormControlLabel value={1} control={<Radio />} label="Macho" />
                      <FormControlLabel value={2} control={<Radio color="secondary" />} label="Fêmea" />
                    </RadioGroup>
                  </FormControl>
                </Grid>
                <Grid item xs={12} sm={6} sx={{ mt: 2 }}>
                  <FormControl component="fieldset" variant="standard">
                    <FormLabel component="legend">Informações de saúde</FormLabel>
                    <FormGroup>
                      <FormControlLabel
                        control={
                          <Switch onChange={handleChange} name="vacinado" />
                        }
                        label="Vacinado"
                      />
                      <FormControlLabel
                        control={
                          <Switch onChange={handleChange} name="castrado" />
                        }
                        label="Castrado"
                      />
                    </FormGroup>
                  </FormControl>
                </Grid>
                <Grid item xs={12} sm={12}>
                  <TextField
                    id="informacoesAdicionais"
                    label="Informações adicionais"
                    multiline
                    rows={4}
                    fullWidth
                    name="informacoesAdicionais"
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs={12} sm={12} style={{
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                }}>
                  <ColorButton variant="contained" component="label" startIcon={<AddPhotoIcon />}>
                    Fotos do Pet
                    <input hidden accept="image/*" multiple type="file" name="fotos"/>
                  </ColorButton>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </DialogContent>
        <DialogActions 
          sx={{ 
            mr: 2
          }}
        >
          <Button variant="outlined" color="error" onClick={handleClose}>
            Cancelar
          </Button>
          <Button type="submit" variant="outlined" color="success" endIcon={<PetsIcon />} onClick={handleSubmit}>
            Salvar
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}