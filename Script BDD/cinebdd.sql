-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2020 a las 22:22:47
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cinebdd`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `popularidad_pelicula` ()  BEGIN
	SELECT titulo, numvotos, valoracion_pelicula(numvotos) as popularidad from pelicula as message; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `p_more_votos` (IN `v_votos` INT(11))  BEGIN
      IF (v_votos < 0) THEN
      	SIGNAL SQLSTATE '43000'
 		SET MESSAGE_TEXT = 'No puede poner valores negativos';
      END IF;
      
      IF (v_votos = 0) THEN
      	SIGNAL SQLSTATE '43000'
 		SET MESSAGE_TEXT = 'No puede poner valor 0';
      END IF;

      SELECT *
      FROM pelicula
      WHERE pelicula.numvotos > v_votos;
      SELECT 'Número de votos superior';
      
      SELECT *
      FROM pelicula
      WHERE pelicula.numvotos < v_votos;
      SELECT 'Número de votos menor';
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `valoracion_pelicula` (`numvotos` INT(11)) RETURNS VARCHAR(60) CHARSET utf8mb4 BEGIN
	declare popularidad varchar(60);

case
	when numvotos < 30 then
	set popularidad = 'escasa popularidad';
	when numvotos < 60 then
	set popularidad = 'media popularidad';
	else
	set popularidad = 'muy popular';
end case;

RETURN popularidad;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `butaca`
--

CREATE TABLE `butaca` (
  `idButaca` int(11) NOT NULL,
  `sala` int(11) DEFAULT NULL,
  `posicionFila` int(11) DEFAULT NULL,
  `posicionColumna` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cine`
--

CREATE TABLE `cine` (
  `idCine` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `imagenCine` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cine`
--

INSERT INTO `cine` (`idCine`, `nombre`, `direccion`, `telefono`, `imagenCine`) VALUES
(1, 'Cinesa', 'Grancasa', '625331447', 'cinesa'),
(2, 'Yelmo Cines', 'Plaza Imperial, Zaragoza', '651332441', 'yelmo'),
(3, 'Cines Palafox', 'Paseo de Independencia 12, Zaragoza', '551224114', 'palafox');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL,
  `usuario` int(11) DEFAULT NULL,
  `sesion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `idpelicula` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `numvotos` int(11) DEFAULT NULL,
  `trailer` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`idpelicula`, `titulo`, `descripcion`, `numvotos`, `trailer`, `imagen`, `genero`, `edad`) VALUES
(3, 'Joker', 'Arthur Fleck es un hombre que se enfrenta a la crueldad y al desprecio de la sociedad. Vive ignorado por un sistema que le permite pasar de la vulnerabilidad a la inmoralidad. Es un payaso que de día trabaja por horas y de noche intenta convertirse en monologuista, pero lo cierto es que siempre acaban burlándose de él. No encaja con la gente que le rodea, como lo demuestra su risa incontrolable y fuera de lugar que le perjudica aún más cuando intenta controlarla. Todo esto lo expone a un ridículo muy peligroso que raya con la violencia. Arthur, que se dedica a cuidar de su frágil madre, busca sin descanso esa figura paterna que nunca tuvo, ya sea el acaudalado empresario Thomas Wayne o el presentador de televisión Murray Franklin.', 64, 'https://youtu.be/ygUHhImN98w', 'joker', 'aventura', 16),
(4, 'It Capitulo 2', 'Han pasado casi 30 años desde que el Club de los Perdedores, formado por Bill, Berverly, Richie, Ben, Eddie, Mike y Stanley, se enfrentaran al macabro y despiadado Pennywise. En cuanto tuvieron oportunidad, abandonaron el pueblo de Derry, en el estado de Maine, que tantos problemas les había ocasionado. Sin embargo, ahora, siendo adultos, parece que no pueden escapar de su pasado. Todos deberán enfrentarse de nuevo al temible payaso para descubrir si de verdad están preparados para superar sus traumas de la infancia.', 100, '', 'it', 'terror', 18),
(5, 'Terminator Destino Oculto', 'Sarah Connor une todas sus fuerzas con una mujer cyborg para proteger a una joven de un extremadamente poderoso y nuevo Terminator.', 50, NULL, 'destino', 'aventura', 16),
(6, 'Malefica Maestra del Mal', 'Secuela de Malefica que cuenta la vida de Malefica y Aurora, asi­ como las alianzas que formaran para sobrevivir a las amenazas del magico mundo en el que habitan.', 67, NULL, 'malefica', 'aventura', 2),
(7, 'Rambo La Última Misión', 'Descripción John Rambo está en horas bajas y viviendo en un rancho en Arizona, pero cuando recibe la noticia de que su nieta ha desaparecido, tras haber cruzado la frontera a México para ir a una fiesta, Rambo decide ir en su búsqueda.', 60, NULL, 'rambo', 'accion', 16),
(8, 'Doctor Sueño', 'Seguimos a Danny Torrace, traumatizado y con problemas de ira y alcoholismo. Estos problemas reflejan los de su propio padre, que cuando tiene sus habilidades psíquicas de vuelta, contacta con una niña, Abra Stone, a la que debe de rescatar de un grupo de viajeros que se alimentan de niños.', 70, NULL, 'doctor', 'terror', 12),
(9, 'Aladdin', 'Aladdin es un ladronzuelo que se enamora de la hija del Sultán, la princesa Jasmine. Para poder conquistarla aceptará un desafío de Jafar. Aladdín tendrá que entrar en una cueva en mitad del desierto y conseguir una lámpara mágica que contiene al Genio que será el encargado de concederle todos sus deseos.', 70, NULL, 'aladdin', 'aventura', 2),
(10, 'El ascenso de SkyWalker', 'La Resistencia sobreviviente se enfrenta a la Primera Orden, y Rey, Finn, Poe y el resto de los héroes encararán nuevos retos y una batalla final con la sabiduría de las generaciones anteriores.', 100, NULL, 'ascenso', 'ciencia Ficcion', 2),
(11, 'John Wick 3', 'John Wick regresa de nuevo pero con una recompensa sobre su cabeza que persigue unos mercenarios. Tras asesinar a uno de los miembros de su gremio, Wick es expulsado y se convierte en el foco de atención de todos los sicarios de la organización.', 80, NULL, 'wick', 'accion', 16),
(12, 'Rocketman', 'Rocketman cuenta la trayectoria del artista Elton John, desde sus años como niño prodigio del piano en la Royal Academy of Music hasta llegar a ser una superestrella de fama mundial gracias a su talento y a la duradera asociacion con su letrista Bernie Taupin.', 90, NULL, 'rocket', 'musical', 16),
(13, 'Hellboy 3', 'Nimue, conocida como La Reina de la Sangre, es antigua hechicera que vuelve a la vida decidida a vengarse de una traicion del pasado. Dividido entre el mundo sobrenatural y humano, el legendario medio demonio Hellboy recibe la mision de contener esa amenaza y salvar al mundo.', 40, NULL, 'hellboy', 'fantasia', 16),
(14, 'Jumanji', 'Las aventuras continuan en el fantastico mundo del juego Jumanji, donde nada es lo que parece. \r\nEn esta ocasión, los jugadores vuelven al juego, pero sus personajes se han intercambiado entre si, \r\nlo que ofrece un curioso plantel: \r\nlos mismos héroes con distinta apariencia. Pero, ¿donde esta el resto de la gente?', 60, NULL, 'jumanji', 'aventuras', 2);

--
-- Disparadores `pelicula`
--
DELIMITER $$
CREATE TRIGGER `film_genero` BEFORE INSERT ON `pelicula` FOR EACH ROW BEGIN
      IF (NEW.genero = 'adulto') THEN
      	SIGNAL SQLSTATE '45000'
 		SET MESSAGE_TEXT = 'No puede insertar peliculas de genero adulto';
      END IF;
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `compra` int(11) DEFAULT NULL,
  `butaca` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `idSala` int(11) NOT NULL,
  `cine` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `filaButaca` int(11) DEFAULT NULL,
  `columnaButaca` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`idSala`, `cine`, `nombre`, `filaButaca`, `columnaButaca`) VALUES
(2, 1, 'cinesa sala uno', 5, 5),
(3, 2, 'yelmo sala uno', 10, 10),
(4, 3, 'palafox sala uno', 8, 8),
(5, 1, 'cinesa sala dos', 20, 20),
(6, 3, 'palafox sala dos', 15, 15),
(7, 2, 'yelmo sala dos', 10, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

CREATE TABLE `sesion` (
  `idSesion` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `hora` varchar(45) DEFAULT NULL,
  `sala` int(11) DEFAULT NULL,
  `pelicula` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sesion`
--

INSERT INTO `sesion` (`idSesion`, `fecha`, `hora`, `sala`, `pelicula`) VALUES
(3, '05/12/2019', '20:00', 2, 3),
(4, '30/11/2019', '16:00', 2, 4),
(5, '30/11/2019', '18:30', 2, 4),
(6, '04/01/2020', '16:30', 4, 7),
(7, '04/01/2020', '19:00', 4, 7),
(8, '01/12/2019', '15:30', 3, 6),
(9, '01/12/2019', '18:00', 3, 5),
(10, '05/12/2019', '23:30', 5, 4),
(11, '06/12/2019', '23:30', 5, 8),
(12, '10/12/2019', '16:00', 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombre`, `apellidos`, `email`, `password`) VALUES
(1, 'Joshua', 'de Nova', 'joshua@gmail.com', '123'),
(2, 'David', 'Riveres', 'rivas@gmail.com', '123'),
(3, 'Vladys', 'Petrus', 'petrus@gmail.com', '1234'),
(10, 'David', 'Mareca', 'david@gmail.com', '1234'),
(13, 'manuel', 'sacacorchos', 'manu@gmail.com', '1234'),
(14, 'a', 'a', 'a', 'a');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `butaca`
--
ALTER TABLE `butaca`
  ADD PRIMARY KEY (`idButaca`),
  ADD KEY `fk_Butaca_Sala1_idx` (`sala`);

--
-- Indices de la tabla `cine`
--
ALTER TABLE `cine`
  ADD PRIMARY KEY (`idCine`),
  ADD UNIQUE KEY `idCine_UNIQUE` (`idCine`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`idCompra`),
  ADD KEY `fk_Compra_Usuario1_idx` (`usuario`),
  ADD KEY `fk_Compra_Sesion1_idx` (`sesion`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`idpelicula`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `fk_Compra_has_Butaca_Butaca1_idx` (`butaca`),
  ADD KEY `fk_Compra_has_Butaca_Compra1_idx` (`compra`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`idSala`),
  ADD KEY `fk_Sala_Cine1_idx` (`cine`);

--
-- Indices de la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD PRIMARY KEY (`idSesion`),
  ADD UNIQUE KEY `idSesion_UNIQUE` (`idSesion`),
  ADD KEY `fk_Sesion_Sala1_idx` (`sala`),
  ADD KEY `fk_Sesion_Pelicula1_idx` (`pelicula`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `butaca`
--
ALTER TABLE `butaca`
  MODIFY `idButaca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cine`
--
ALTER TABLE `cine`
  MODIFY `idCine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `idCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `idpelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `idSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `sesion`
--
ALTER TABLE `sesion`
  MODIFY `idSesion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `butaca`
--
ALTER TABLE `butaca`
  ADD CONSTRAINT `fk_Butaca_Sala1` FOREIGN KEY (`sala`) REFERENCES `sala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_Compra_Sesion1` FOREIGN KEY (`sesion`) REFERENCES `sesion` (`idSesion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_Usuario1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_Compra_has_Butaca_Butaca1` FOREIGN KEY (`butaca`) REFERENCES `butaca` (`idButaca`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_has_Butaca_Compra1` FOREIGN KEY (`compra`) REFERENCES `compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `fk_Sala_Cine1` FOREIGN KEY (`cine`) REFERENCES `cine` (`idCine`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD CONSTRAINT `fk_Sesion_Pelicula1` FOREIGN KEY (`pelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Sesion_Sala1` FOREIGN KEY (`sala`) REFERENCES `sala` (`idSala`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
