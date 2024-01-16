package com.example.layouts.models;

import java.time.LocalDate;

public class GeoPunto {
    private Double longitud;
    private Double latitud;
    private Byte [] imagen; // Codigo ejemplo abaja de la clase
    private String url;
    private String comentario;
    private LocalDate fecha;
    private Integer valoracion;
    private TipoLugar tipoLugar;


    public GeoPunto() {
    }

    public GeoPunto(Double longitud, Double latitud, Byte[] imagen, String url, String comentario, LocalDate fecha, Integer valoracion, TipoLugar tipoLugar) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.imagen = imagen;
        this.url = url;
        this.comentario = comentario;
        this.fecha = fecha;
        this.valoracion = valoracion;
        this.tipoLugar = tipoLugar;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Byte[] getImagen() {
        return imagen;
    }

    public void setImagen(Byte[] imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }
    @Override
    public String toString() {
        return "GeoPunto{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                ", imagen=" + imagen +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", valoracion=" + valoracion +
                ", tipoLugar=" + tipoLugar +
                '}';
    }
}

//      File a1= new File("foto.jpeg");
//		File a2= new File("CopiaImagen.jpeg");
//		Byte b[]=new Byte[0];
//		try {
//			DataInputStream in = new DataInputStream(new FileInputStream(a1));
//			Byte a;
//			while(true) {
//				a=in.readByte();
//				b=Arrays.copyOf(b, b.length+1);
//				b[b.length-1]=a;
//			}
//		}catch(EOFException e) {
//			System.out.println("Fin lectura");
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			DataOutputStream out=new DataOutputStream(new FileOutputStream(a2));
//			for(Byte i: b) {
//				out.writeByte(i);
//			}
//			out.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
