@echo off
:loop
java Socketor client 127.0.0.1 8000 %time:~6,2% %time:~9,2%
goto loop