package the_fireplace.configyaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;
import org.yaml.snakeyaml.serializer.Serializer;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An extension of Yaml designed for writing config files easier.
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ConfigYaml extends Yaml {
    public ConfigYaml() {
        super();
    }

    public ConfigYaml(DumperOptions dumperOptions) {
        super(dumperOptions);
    }

    public ConfigYaml(LoaderOptions loadingConfig) {
        super(loadingConfig);
    }

    public ConfigYaml(Representer representer) {
        super(representer);
    }

    public ConfigYaml(BaseConstructor constructor) {
        super(constructor);
    }

    public ConfigYaml(BaseConstructor constructor, Representer representer) {
        super(constructor, representer);
    }

    public ConfigYaml(Representer representer, DumperOptions dumperOptions) {
        super(representer, dumperOptions);
    }

    public ConfigYaml(BaseConstructor constructor, Representer representer, DumperOptions dumperOptions) {
        super(constructor, representer, dumperOptions);
    }

    public ConfigYaml(BaseConstructor constructor, Representer representer, DumperOptions dumperOptions, LoaderOptions loadingConfig) {
        super(constructor, representer, dumperOptions, loadingConfig);
    }

    public ConfigYaml(BaseConstructor constructor, Representer representer, DumperOptions dumperOptions, Resolver resolver) {
        super(constructor, representer, dumperOptions, resolver);
    }

    public ConfigYaml(BaseConstructor constructor, Representer representer, DumperOptions dumperOptions, LoaderOptions loadingConfig, Resolver resolver) {
        super(constructor, representer, dumperOptions, loadingConfig, resolver);
    }

    @Override
    public String dumpAll(Iterator<?> data) {
        StringWriter buffer = new StringWriter();
        dumpAll(data, buffer, null);
        return buffer.toString();
    }

    @Override
    public void dump(Object data, Writer output) {
        List<Object> list = new ArrayList<>();
        list.add(data);
        dumpAll(list.iterator(), output, null);
    }

    @Override
    public void dumpAll(Iterator<?> data, Writer output) {
        dumpAll(data, output, null);
    }

    @SuppressWarnings("WeakerAccess")
    protected void dumpAll(Iterator<?> data, Writer output, Tag rootTag) {
        ConfigEmitter e = new ConfigEmitter(output, dumperOptions);
        Serializer serializer = new Serializer(e, resolver, dumperOptions, rootTag);
        try {
            serializer.open();
            while (data.hasNext()) {
                Object parsing = data.next();
                e.setCommentMap(parsing);
                Node node = representer.represent(parsing);
                serializer.serialize(node);
            }
            serializer.close();
        } catch (IOException ex) {
            throw new YAMLException(ex);
        }
    }

    @Override
    public String dumpAs(Object data, Tag rootTag, DumperOptions.FlowStyle flowStyle) {
        DumperOptions.FlowStyle oldStyle = representer.getDefaultFlowStyle();
        if (flowStyle != null)
            representer.setDefaultFlowStyle(flowStyle);
        List<Object> list = new ArrayList<>(1);
        list.add(data);
        StringWriter buffer = new StringWriter();
        dumpAll(list.iterator(), buffer, rootTag);
        representer.setDefaultFlowStyle(oldStyle);
        return buffer.toString();
    }
}
