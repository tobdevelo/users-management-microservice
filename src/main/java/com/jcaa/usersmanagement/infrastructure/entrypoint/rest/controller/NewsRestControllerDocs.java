package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.config.OpenApiConfig;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.NewsRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Noticias", description = "Gestión CRUDL de noticias: crear, consultar, actualizar y eliminar.")
@SecurityRequirement(name = OpenApiConfig.BEARER_AUTH)
public interface NewsRestControllerDocs {

    @Operation(summary = "Crear noticia", description = "Registra una nueva noticia en el sistema.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Noticia creada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    NewsRestResponse create(@Valid @RequestBody CreateNewsRestRequest request);

    @Operation(summary = "Listar todas las noticias", description = "Retorna todas las noticias ordenadas por fecha.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de noticias obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NewsRestResponse.class))))
    })
    List<NewsRestResponse> getAll();

    @Operation(summary = "Obtener noticia por ID", description = "Retorna una noticia específica por su UUID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Noticia encontrada.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsRestResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Noticia no encontrada.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    NewsRestResponse getById(
            @Parameter(description = "UUID de la noticia.", required = true) @PathVariable String id);

    @Operation(summary = "Actualizar noticia", description = "Actualiza los datos de una noticia existente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Noticia actualizada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsRestResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Noticia no encontrada.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    NewsRestResponse update(
            @Parameter(description = "UUID de la noticia.", required = true) @PathVariable String id,
            @Valid @RequestBody UpdateNewsRestRequest request);

    @Operation(summary = "Eliminar noticia", description = "Elimina permanentemente una noticia por su UUID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Noticia eliminada exitosamente."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Noticia no encontrada.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void delete(
            @Parameter(description = "UUID de la noticia.", required = true) @PathVariable String id);
}