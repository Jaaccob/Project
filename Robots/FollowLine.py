#!/usr/bin/env python3

from ev3dev.ev3 import *

cl = ColorSensor()
left = LargeMotor(OUTPUT_B)
right = LargeMotor(OUTPUT_C)
btn = Button()


def calibrate():
    print("Na bialy")
    while True:
        if btn.any():
            white = cl.value()
            break

    print("Na czarny")
    while True:
        if btn.any():
            black = cl.value()
            break
    return (white + black) / 2


def two_state():
    while cl.value() != 5:
        cl.mode = 'COL-REFLECT'
        e = calibrate() - cl.value()
        if e < 0:
            left.run_forever(speed_sp=390)
            right.run_forever(speed_sp=150)
        else:
            left.run_forever(speed_sp=150)
            right.run_forever(speed_sp=390)
        cl.mode = 'COL-COLOR'
    left.stop()
    right.stop()


def proportional():
    kp = 10
    while cl.value() != 5:
        cl.mode = 'COL-REFLECT'
        e = calibrate() - cl.value()
        left.run_forever(speed_sp=325 - (kp * e))
        right.run_forever(speed_sp=325 + (kp * e))
        cl.mode = 'COL-COLOR'
    left.stop()
    right.stop()


def PID_regulator():
    kp = 15
    suma = 0
    old_e = 0
    ki = 0.2
    kd = 0.5
    while cl.value() != 5:
        cl.mode = 'COL-REFLECT'
        e = calibrate() - cl.value()
        suma += e
        alfa = kp * e + ki * suma + kd * old_e
        old_e = e
        left.run_forever(speed_sp=500 - alfa)
        right.run_forever(speed_sp=500 + alfa)
        cl.mode = 'COL-COLOR'
    left.stop()
    right.stop()


print("Który program chcesz wybrać? ")
print("1. Regulator dwustawny")
print("2. Regulator proporcjonalny")
print("3. Regulator PID")
choose = input()
fun = [two_state(), proportional(), PID_regulator()]
while True:
    if btn.any():
        break
fun[choose]
