# Scrapers ideas
1. snapshot, query box2d world, send packed data
2. receive inputs, store in queue, apply all of them at once, clear queue
3. vehicle dsl, materialization
4. input dsl
5. map dsl


### World
- gravity
- add/remove bodies/fixtures/joint
- query aabb
- apply forces, impulses, torques, joint motors

### Body
- angle
- position
- body type
- fixtures
- user data
- _mass_
- _rotational inertia_
- _velocity_
- _angular velocity_
- _gravity scale_
- _linear / angular dampening_

### Fixture
- radius / coords
- shape type
- user data
- _material_

### Material
- restitution
- density
- friction

### Joint
- prismatic / revolute
- body-a
- body-b
- anchor-a
- anchor-b
- collide-connected

### Concepts
- account id <-> channels bimap
- key <-> vehicle action -> script

### Architecture
- auth server: http login with username / password, receive token
- game server
