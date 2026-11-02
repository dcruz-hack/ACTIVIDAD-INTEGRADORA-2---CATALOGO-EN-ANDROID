# ZM TECHNOLOGY V1

Aplicación móvil Flutter que moderniza el catálogo de productos de ZM TECHNOLOGY e incorpora servicios nativos de conectividad, cámara/galería y notificaciones locales.

> **Continuidad del proyecto:** esta entrega parte de la aplicación `catalogo_flutter` desarrollada en la Actividad Integradora 1. Se conserva el catálogo y la navegación de productos, y se realiza la reingeniería en la Actividad Integradora 2.

## Tecnologías

- Flutter / Dart
- Provider para gestión de estado
- `connectivity_plus` para escuchar cambios de conectividad y `internet_connection_checker_plus` para verificar acceso real a Internet
- `image_picker`, `permission_handler` e `image_cropper` para captura, selección, permisos y procesamiento de imágenes
- `flutter_local_notifications` para notificaciones locales y canal Android
- `shared_preferences` para persistencia local

## Arquitectura


UI (screens / widgets)
        │ Provider
        ▼
AppProvider (estado y coordinación)
        ├── ConnectivityService ── connectivity_plus
        ├── CameraService ──────── permission_handler / image_picker / image_cropper
        ├── NotificationService ── flutter_local_notifications
        └── StorageService ─────── shared_preferences

Modelos: Product
Tema: AppTheme


Los servicios se encuentran en `lib/services/` y no dependen de widgets. `AppProvider` coordina su estado y expone los cambios a la interfaz.

## Requisitos

- Flutter SDK compatible con Dart `^3.13.5` (según `pubspec.yaml`).
- Android Studio / Android SDK o Xcode para iOS.
- Dispositivo físico o emulador con permisos de cámara/fotos y notificaciones.

## Instalación y ejecución


git clone <https://github.com/dcruz-hack/ACTIVIDAD-INTEGRADORA-2---CATALOGO-EN-ANDROID.git>
cd catalogo_flutter
flutter pub get
flutter run


Para generar APK de prueba:


flutter build apk --debug


## Pruebas manuales QA

1. **Conectividad:** abrir la app y desactivar Wi-Fi/datos desde el dispositivo. Debe mostrarse el banner rojo de modo sin conexión. `connectivity_plus` detecta cambios de interfaz y `internet_connection_checker_plus` verifica acceso a Internet. La disponibilidad de un servidor específico puede variar independientemente.
2. **Cámara:** pulsar “Abrir cámara / galería” → “Tomar fotografía”. Aceptar el permiso, capturar y ajustar la imagen.
3. **Galería:** elegir “Elegir de galería”, conceder acceso y seleccionar/recortar una imagen.
4. **Notificaciones:** pulsar el icono de campana de la barra superior. Debe recibirse una notificación local; en Android se usa el canal `zm_events`.
5. **Errores de permisos:** denegar el permiso y comprobar el mensaje mostrado. Si queda denegado permanentemente, habilitarlo desde Ajustes de la aplicación.

## Permisos declarados

Android: `INTERNET`, `ACCESS_NETWORK_STATE`, `CAMERA`, `POST_NOTIFICATIONS` y `READ_MEDIA_IMAGES`.

iOS: descripciones de uso de cámara y biblioteca de fotos en `Info.plist`. La solicitud de notificaciones se realiza al inicializar el servicio.

## Estructura relevante


lib/
├── main.dart
├── models/product.dart
├── providers/app_provider.dart
├── screens/
├── services/
│   ├── camera_service.dart
│   ├── connectivity_service.dart
│   ├── notification_service.dart
│   └── storage_service.dart
├── theme/app_theme.dart
└── widgets/
    └── offline_banner.dart

## Autor

Deivid Cruz

