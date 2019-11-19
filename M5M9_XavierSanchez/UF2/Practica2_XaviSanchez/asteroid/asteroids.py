
# ! / usr / bin / env python

matemáticas de importación

importar pygame

importar al azar
de pygame.locals import  *


clase  mundial ( objeto ):


   RENDER_OPTIONS  =  HWSURFACE  |  DOUBLEBUF  |  RESIZABLE
   NEGRO  = ( 0 , 0 , 0 )
   BLANCO  = ( 255 , 255 , 255 )

   def  __init__ ( auto , tamaño , jugador ):
       # configurando la pantalla
       self .size = tamaño
       self .surface = pygame.display.set_mode (tamaño, self . RENDER_OPTIONS )
       self .surface.fill ( self . NEGRO )

       # esconde el sprite del jugador
       self .player = jugador

       # agregar un grupo de sprites
       self .sprites = pygame.sprite.RenderUpdates ()
       self .sprites.add (jugador)

       # configurar nuestros controladores de eventos
       self .event_handlers = {
           VIDEORESIZE : self .handle_resize,
           # KEYDOWN: self.handle_keydown,
           # KEYUP: self.handle_keyup
       }

    actualización de def ( auto ):
       # permitir que los sprites se actualicen
       self .sprites.update ()

       # cambia la ubicación del sprite para que coincida con su movimiento correcto
       para sprite en  self .sprites:
           # agarra la siguiente posición en la que debería estar el sprite
           new_center = Vector.from_position (sprite.rect.center) + sprite.motion
           new_center = new_center.to_position ()

           # hacer el ajuste de pantalla para las posiciones x e y
           x = new_center [ 0 ] %  self .size [ 0 ]
           y = new_center [ 1 ] %  self .size [ 1 ]
           sprite.rect.center = (x, y)

   def  render ( self ):
       def  clear_callback ( superficie , rect ):
           relleno de superficie ( auto . NEGRO , rect)

       self .sprites.clear ( self .surface, clear_callback)
       updatedRects =  self .sprites.draw ( self .surface)
       pygame.display.update (updatedRects)

   def  handle_event ( self , event ):
       handler =  self .event_handlers.get (event.type, lambda  x : None )
       controlador (evento)

   def  handle_resize ( self , event ):
       self .size = event.dict [ ' tamaño ' ]
       self .surface = pygame.display.set_mode ( self .size, self . RENDER_OPTIONS )


Vector de clase ( objeto ):

   def  __init__ ( self , x , y ):
       auto .x = x
       self .y = y

    magnitud de def ( auto ):
       return math.sqrt ( self .x *  self .x +  self .y *  self .y)

   def  normalizar ( auto ):
       return  self  /  self .magnitude ()

   def  __add__ ( self , otro ):
       x =  auto .x + otro.x
       y =  self .y + other.y
       Vector de retorno (x, y)

   def  __sub__ ( self , otro ):
       x =  self .x - otro.x
       y =  self .y - other.y
       Vector de retorno (x, y)

   def  __mul__ ( self , n ):
       x =  auto .x * n
       y =  auto .y * n
       Vector de retorno (x, y)

   def  __div__ ( self , n ):
       x =  auto .x / n
       y =  auto .y / n
       Vector de retorno (x, y)

   def  __repr__ ( self ):
       devuelve  " Vector ( {} , {} ) " .format ( self .x, self .y)

   def  to_position ( self ):
       retorno ( self .x, self .y)

   def  to_radians ( self ):
       radianes = math.atan2 ( self .x, self .y)
       retorno (radianes, auto .magnitud ())

   def  to_degrees ( self ):
       radianes, magnitud =  self .to_radians ()
       retorno (grados matemáticos (radianes), magnitud)

   @ classmethod
   def  from_position ( self , position ):
       Vector de retorno (posición [ 0 ], posición [ 1 ])

   @ classmethod
   def  from_radians ( self , radianes , magnitud = 1 ):
       Vector de retorno (math.sin (radianes), math.cos (radianes)) * magnitud

   @ classmethod
   def  from_degrees ( self , grados , magnitud = 1 ):
       return Vector.from_radians (math.radians (grados), magnitud)


Entidad de clase ( pygame . sprite . Sprite ):

   def  __init__ ( self , imagen , posición ):
       super (entidad, auto ). __init__ ()
       self .image = imagen
       self .rect =  self .image.get_rect ()
       self .rect.center = position
       self .motion = Vector ( 0 , 0 )

Asteroide clase ( Entidad ):
   def  __init__ ( self , position ):
       self .orig_image = pygame.image.load ( ' recursos / asteroid.png ' )
       super (asteroide, auto ). __init__ ( self .orig_image, posición)

       x = random.randint ( - 10 , 10 )
       y = random.randint ( - 10 , 10 )
       self .motion = Vector (x, y)
       auto .duration =  100

    actualización de def ( auto ):
       auto duración - =  1
       si  auto .duration <= 0 :
           self .kill ()



Clase  Bullet ( Entidad ):
   def  __init__ ( auto , posición , dirección , magnitud ):
       self .orig_image = pygame.image.load ( ' recursos / bullet.png ' )
       super (Bullet, self ). __init__ ( self .orig_image, posición)
       self .motion = Vector.from_degrees (dirección, magnitud)
       auto .duration =  100
    actualización de def ( auto ):
       auto duración - =  1
       si  auto .duration <= 0 :
           self .kill ()


Clase  Jugador ( Entidad ):

   def  __init__ ( self , position ):
       self .orig_image = pygame.image.load ( ' recursos / ship.png ' )
       super (jugador, auto ). __init__ ( self .orig_image, posición)
       self .facing = Vector.from_degrees ( 90 )
       self .forward =  False
       self .backward =  False
       self .turn_left =  False
       self .turn_right =  False
       self .accel =  0.15

    actualización de def ( auto ):
       # si estamos empujando, agregue el vector de nuestra cara al movimiento
       si es  propio .
           self .motion =  self .motion -  self .facing *  self .accel

       si es  auto. hacia atrás:
           self .motion =  self .motion +  self .facing *  self .accel

       grados, _ =  self .facing.to_degrees ()
       if  self .turn_left:
           grados = (grados +  10 ) %  360

       si  auto .turn_right:
           grados = (grados -  10 ) %  360

       self .facing = Vector.from_degrees (grados)

       # rotar nuestro sprite para que coincida con nuestra dirección, y ponerlo en el lugar correcto
       current =  self .rect.center
       self .image = pygame.transform.rotate ( self .orig_image, grados)
       self .rect =  self .image.get_rect ()
       self .rect.center = current


def  main ():


   # setup pygame
   pygame.init ()
   pygame.font.init ()
   pygame.mixer.init ()
   pygame.display.set_caption ( " Asteroides 0.2 " )

   # almacena nuestro estado del juego
   jugador = jugador (( 400 , 300 ))
   mundo = Mundo (( 800 , 600 ), jugador)
   world.pew = pygame.mixer.Sound ( ' recursos / pew.wav ' )



   # usa el reloj para acelerar los fps a algo razonable
   clock = pygame.time.Clock ()
   contador =  0
   tiempo =  0
   # bucle principal
   corriendo =  verdadero
   mientras se ejecuta:
       tiempo + =  1
       eventos = pygame.event.get ()
       if (tiempo ==  10  y  len (world.sprites) <  31 ):
           asteroide = asteroide ((random.randint ( 0 , 800 ), random.randint ( 0 , 600 )))
           world.sprites.add (asteroide)
           contador + =  1
           tiempo =  0
       # manejar nuestros eventos
       para evento en eventos:
           if event.type ==  QUIT :
               corriendo =  falso
               rotura

           world.handle_event (evento)

       world.update ()
       world.render ()
       pygame.display.flip ()
       clock.tick ( 40 )


if  __name__  ==  " __main__ " :
   principal()
