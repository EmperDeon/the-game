/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.basemod.interfaces;

import mods.basemod.containers.Mid;
import render.Tex;

/**
 *
 * @author ilya
 */
public interface InvItem extends Base{

 public String getparam(String k);

 public Tex getTex();

 public Mid getId();
}
