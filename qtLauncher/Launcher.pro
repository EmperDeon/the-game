QT      +=  webkit network
HEADERS =   mainwindow.h \
    logger.h
SOURCES =   main.cpp \
            mainwindow.cpp \
    logger.cpp

# install
target.path = $$[QT_INSTALL_EXAMPLES]/webkit/fancybrowser
sources.files = $$SOURCES $$HEADERS $$RESOURCES *.pro
sources.path = $$[QT_INSTALL_EXAMPLES]/webkit/fancybrowser
INSTALLS += target sources

FORMS +=

